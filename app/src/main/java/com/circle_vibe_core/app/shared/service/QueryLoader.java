package com.circle_vibe_core.app.shared.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class QueryLoader {

    public String load(String path) {
        try (InputStream is =
                     getClass().getClassLoader()
                             .getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Query not found: " + path);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load query: " + path, e);
        }
    }
}