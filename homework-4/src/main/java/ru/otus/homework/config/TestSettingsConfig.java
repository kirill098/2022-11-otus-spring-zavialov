package ru.otus.homework.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "test")
public class TestSettingsConfig {

    private String fileName;
    private Float passingScore;
}
