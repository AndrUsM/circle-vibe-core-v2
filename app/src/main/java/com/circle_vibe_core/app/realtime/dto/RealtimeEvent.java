package com.circle_vibe_core.app.realtime.dto;

import java.time.Instant;
import java.util.UUID;

import com.circle_vibe_core.app.realtime.enums.RealtimeEventType;

public record RealtimeEvent<T>(
        UUID eventId,
        RealtimeEventType type,
        T payload,
        Instant timestamp
) {

    public static <T> RealtimeEvent<T> of(
            RealtimeEventType type,
            T payload
    ) {
        return new RealtimeEvent<>(
                UUID.randomUUID(),
                type,
                payload,
                Instant.now()
        );
    }
}