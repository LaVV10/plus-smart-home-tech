package ru.yandex.practicum.analyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.analyzer.model.*;
import ru.yandex.practicum.analyzer.repository.ScenarioRepository;
import ru.yandex.practicum.analyzer.repository.SensorRepository;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HubEventService {
    private final SensorRepository sensorRepository;
    private final ScenarioRepository scenarioRepository;

    public HubEventService(SensorRepository sensorRepository,
                           ScenarioRepository scenarioRepository) {
        this.sensorRepository = sensorRepository;
        this.scenarioRepository = scenarioRepository;
    }

    @Transactional
    public void handle(HubEventAvro event) {
        Object payload = event.getPayload();

        if (payload instanceof DeviceAddedEventAvro deviceAddedEvent) {
            addSensor(event.getHubId(), deviceAddedEvent);
            return;
        }

        if (payload instanceof DeviceRemovedEventAvro deviceRemovedEvent) {
            removeSensor(event.getHubId(), deviceRemovedEvent);
            return;
        }

        if (payload instanceof ScenarioAddedEventAvro scenarioAddedEvent) {
            addScenario(event.getHubId(), scenarioAddedEvent);
            return;
        }

        if (payload instanceof ScenarioRemovedEventAvro scenarioRemovedEvent) {
            removeScenario(event.getHubId(), scenarioRemovedEvent);
        }
    }

    private void addSensor(String hubId, DeviceAddedEventAvro event) {
        if (sensorRepository.existsById(event.getId())) {
            return;
        }

        Sensor sensor = new Sensor(event.getId(), hubId);
        sensorRepository.save(sensor);
    }

    private void removeSensor(String hubId, DeviceRemovedEventAvro event) {
        sensorRepository.findByIdAndHubId(event.getId(), hubId)
                .ifPresent(sensorRepository::delete);
    }

    private void addScenario(String hubId, ScenarioAddedEventAvro event) {
        scenarioRepository.findByHubIdAndName(hubId, event.getName())
                .ifPresent(scenarioRepository::delete);

        Scenario scenario = new Scenario(hubId, event.getName());

        addConditions(scenario, event.getConditions());
        addActions(scenario, event.getActions());

        scenarioRepository.save(scenario);
    }

    private void addConditions(Scenario scenario, List<ScenarioConditionAvro> conditions) {
        if (conditions.isEmpty()) {
            return;
        }

        Set<String> sensorIds = conditions.stream()
                .map(ScenarioConditionAvro::getSensorId)
                .collect(Collectors.toSet());

        List<Sensor> existingSensors = sensorRepository.findByHubIdAndIdIn(scenario.getHubId(), sensorIds);
        Map<String, Sensor> sensorMap = existingSensors.stream()
                .collect(Collectors.toMap(Sensor::getId, s -> s));

        List<Sensor> newSensors = sensorIds.stream()
                .filter(id -> !sensorMap.containsKey(id))
                .map(id -> new Sensor(id, scenario.getHubId()))
                .collect(Collectors.toList());

        if (!newSensors.isEmpty()) {
            sensorRepository.saveAll(newSensors);
            // Добавляем в мапу, чтобы использовать ниже
            newSensors.forEach(s -> sensorMap.put(s.getId(), s));
        }

        for (ScenarioConditionAvro conditionAvro : conditions) {
            Sensor sensor = sensorMap.get(conditionAvro.getSensorId());

            Condition condition = new Condition(  // ← теперь ваш Condition
                    conditionAvro.getType().name(),
                    conditionAvro.getOperation().name(),
                    toIntegerValue(conditionAvro.getValue())
            );

            ScenarioCondition scenarioCondition = new ScenarioCondition(scenario, sensor, condition);
            scenario.getConditions().add(scenarioCondition);
        }
    }

    private void addActions(Scenario scenario, List<DeviceActionAvro> actions) {
        if (actions.isEmpty()) {
            return;
        }

        Set<String> sensorIds = actions.stream()
                .map(DeviceActionAvro::getSensorId)
                .collect(Collectors.toSet());

        List<Sensor> existingSensors = sensorRepository.findByHubIdAndIdIn(scenario.getHubId(), sensorIds);
        Map<String, Sensor> sensorMap = existingSensors.stream()
                .collect(Collectors.toMap(Sensor::getId, s -> s));

        List<Sensor> newSensors = sensorIds.stream()
                .filter(id -> !sensorMap.containsKey(id))
                .map(id -> new Sensor(id, scenario.getHubId()))
                .collect(Collectors.toList());

        if (!newSensors.isEmpty()) {
            sensorRepository.saveAll(newSensors);
            newSensors.forEach(s -> sensorMap.put(s.getId(), s));
        }

        for (DeviceActionAvro actionAvro : actions) {
            Sensor sensor = sensorMap.get(actionAvro.getSensorId());

            Action action = new Action(
                    actionAvro.getType().name(),
                    actionAvro.getValue()
            );

            ScenarioAction scenarioAction = new ScenarioAction(scenario, sensor, action);
            scenario.getActions().add(scenarioAction);
        }
    }

    private void removeScenario(String hubId, ScenarioRemovedEventAvro event) {
        scenarioRepository.deleteByHubIdAndName(hubId, event.getName());
    }

    private Integer toIntegerValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer integerValue) {
            return integerValue;
        }

        if (value instanceof Boolean booleanValue) {
            return booleanValue ? 1 : 0;
        }

        throw new IllegalArgumentException("Unsupported condition value type: " + value.getClass());
    }
}
