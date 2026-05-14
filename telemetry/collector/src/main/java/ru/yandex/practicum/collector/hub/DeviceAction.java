package ru.yandex.practicum.collector.hub;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonTypeName("DEVICE_ACTION")
public class DeviceAction {
    @NotBlank
    private String sensorId;

    @NotNull
    private ActionType type;

    private Integer value;
}
