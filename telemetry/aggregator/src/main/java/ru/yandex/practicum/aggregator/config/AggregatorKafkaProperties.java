package ru.yandex.practicum.aggregator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aggregator.kafka")
public class AggregatorKafkaProperties {
    private String bootstrapServers;
    private String sensorsTopic;
    private String snapshotsTopic;
    private String groupId;
}
