package com.oussema.keylearn.users.infrastructure.dto.response;

import com.oussema.keylearn.users.infrastructure.entity.PhoneNumber;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record AdminDto(
    UUID id,
    String email,
    String fullName,
    String firstName,
    String lastName,
    PhoneNumber phoneNumber,
    boolean enabled,
    boolean emailVerified,
    List<String> roles,
    Instant createdAt,
    Instant updatedAt,
    short version) {}
