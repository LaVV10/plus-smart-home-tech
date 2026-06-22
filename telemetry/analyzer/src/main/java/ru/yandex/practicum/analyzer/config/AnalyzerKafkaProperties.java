package ru.yandex.practicum.analyzer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "analyzer.kafka")
public class AnalyzerKafkaProperties {
    private String bootstrapServers;
    private String snapshotsTopic;
    private String hubsTopic;
    private String snapshotsGroupId;
    private String hubsGroupId;
}
