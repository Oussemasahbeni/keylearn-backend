package com.oussema.keylearn.users.infrastructure.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddAdminRequest(
    @Email(message = "Invalid email address")
        @NotNull(message = "Email is required")
        @NotBlank(message = "Email is required")
        String email,
    @NotNull(message = "First name is required")
        @NotBlank(message = "First name is required")
        @Size(min = 2, message = "First name must be at least 2 characters long")
        String firstName,
    @NotNull(message = "Last name is required")
        @NotBlank(message = "Last name is required")
        @Size(min = 2, message = "Last name must be at least 2 characters long")
        String lastName) {}
