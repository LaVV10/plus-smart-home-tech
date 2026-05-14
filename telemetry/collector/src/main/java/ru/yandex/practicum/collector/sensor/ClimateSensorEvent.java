package ru.yandex.practicum.collector.sensor;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@JsonTypeName("CLIMATE_SENSOR_EVENT")
public class ClimateSensorEvent extends SensorEvent {
    private int temperatureC;
    private int humidity;
    private int co2Level;

    @Override
    public SensorEventType getType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
