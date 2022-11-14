package com.substringsearcher.app.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.substringsearcher.app")
@EnableConfigurationProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForcedDelayConfiguration {

    private Integer forcedDelay;

}
