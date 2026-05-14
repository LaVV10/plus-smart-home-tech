package ru.yandex.practicum.collector.sensor;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@JsonTypeName("MOTION_SENSOR_EVENT")
public class MotionSensorEvent extends SensorEvent {
    private int linkQuality;
    private boolean motion;
    private int voltage;

    @Override
    public SensorEventType getType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
