package com.nexters.dailyphrase.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.nexters.dailyphrase.common.jwt.JwtProperties;

@EnableConfigurationProperties({
    JwtProperties.class,
})
@Configuration
public class ConfigurationPropertiesConfig {}
