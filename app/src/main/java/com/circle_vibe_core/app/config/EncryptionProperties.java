package com.circle_vibe_core.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.encryption")
public record EncryptionProperties(String secretKey) {
}
