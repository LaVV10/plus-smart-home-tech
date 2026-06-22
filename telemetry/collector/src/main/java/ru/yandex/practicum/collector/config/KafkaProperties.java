package ru.yandex.practicum.collector.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "collector.kafka")
public class KafkaProperties {
    private String bootstrapServers;
    private String sensorsTopic;
    private String hubsTopic;
}
