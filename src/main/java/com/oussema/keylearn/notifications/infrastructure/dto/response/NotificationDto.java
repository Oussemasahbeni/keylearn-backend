package com.oussema.keylearn.notifications.infrastructure.dto.response;

import com.oussema.keylearn.notifications.domain.enums.NotificationType;

import java.time.Instant;
import java.util.Map;

public record NotificationDto(
    String id,
    String userId,
    Boolean isRead,
    Map<String, Object> data,
    NotificationType type,
    Instant createdAt,
    Instant updatedAt) {}
