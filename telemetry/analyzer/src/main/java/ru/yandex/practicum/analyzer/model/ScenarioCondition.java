package ru.yandex.practicum.analyzer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "scenario_conditions")
@Getter
@Setter
@ToString(exclude = {"scenario", "sensor", "condition"})
@NoArgsConstructor
public class ScenarioCondition {
    @EmbeddedId
    private ScenarioConditionId id = new ScenarioConditionId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("scenarioId")
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sensorId")
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("conditionId")
    @JoinColumn(name = "condition_id")
    private Condition condition;

    public ScenarioCondition(Scenario scenario, Sensor sensor, Condition condition) {
        this.scenario = scenario;
        this.sensor = sensor;
        this.condition = condition;
    }
}
