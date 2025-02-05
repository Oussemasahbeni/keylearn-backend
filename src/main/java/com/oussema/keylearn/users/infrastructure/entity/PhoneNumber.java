package com.oussema.keylearn.users.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Embeddable
public record PhoneNumber(
    @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\d{8}", message = "Phone number must be exactly 8 digits long")
        @Column(name = "phone_number")
        String number,
    @NotBlank(message = "Phone number is required")
        @NotNull
        @Pattern(
            regexp = "\\+\\d{1,4}",
            message = "Country code must start with '+' and be followed by 1 to 4 digits")
        @Column(name = "country_code")
        String countryCode) {}
