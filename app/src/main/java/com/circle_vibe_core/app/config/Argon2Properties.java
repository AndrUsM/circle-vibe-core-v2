package com.circle_vibe_core.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.password.argon2")
public record Argon2Properties(
        int saltLength,
        int hashLength,
        int parallelism,
        int memory,
        int iterations
) {
}
