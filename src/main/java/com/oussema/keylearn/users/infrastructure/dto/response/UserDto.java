package com.oussema.keylearn.users.infrastructure.dto.response;

import com.oussema.keylearn.users.domain.enums.Gender;
import com.oussema.keylearn.users.domain.enums.NotificationPreference;
import com.oussema.keylearn.users.infrastructure.entity.PhoneNumber;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserDto(
    UUID id,
    String email,
    String fullName,
    String firstName,
    String lastName,
    String profilePicture,
    PhoneNumber phoneNumber,
    LocalDate dateOfBirth,
    Gender gender,
    NotificationPreference notificationPreference,
    String address,
    String country,
    boolean enabled,
    boolean emailVerified,
    boolean accountLocked,
    List<String> roles,
    Instant createdAt,
    Instant updatedAt,
    short version) {}
