package com.oussema.keylearn.users.infrastructure.dto.response;

import com.oussema.keylearn.users.domain.enums.Gender;
import com.oussema.keylearn.users.infrastructure.entity.PhoneNumber;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateProfileResponse(
    UUID id,
    String firstName,
    String lastName,
    String email,
    PhoneNumber phoneNumber,
    String address,
    LocalDate dateOfBirth,
    Gender gender) {}
