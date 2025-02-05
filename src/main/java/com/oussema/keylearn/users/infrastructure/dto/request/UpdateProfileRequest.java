package com.oussema.keylearn.users.infrastructure.dto.request;

import com.oussema.keylearn.users.domain.enums.Gender;
import com.oussema.keylearn.users.infrastructure.entity.PhoneNumber;
import jakarta.annotation.Nullable;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateProfileRequest(
    UUID id,
    String firstName,
    String lastName,
    // @Min(value = 8, message = "Phone number must be exactly 8 digits long")
    @Nullable PhoneNumber phoneNumber,
    @Nullable String address,
    @Nullable LocalDate dateOfBirth,
    @Nullable Gender gender,
    String country) {}
