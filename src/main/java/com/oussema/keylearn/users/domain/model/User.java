package com.oussema.keylearn.users.domain.model;

import static org.springframework.util.StringUtils.capitalize;

import com.oussema.keylearn.shared.AbstractAuditingModel;
import com.oussema.keylearn.users.domain.enums.Gender;
import com.oussema.keylearn.users.domain.enums.NotificationPreference;
import com.oussema.keylearn.users.infrastructure.entity.PhoneNumber;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AbstractAuditingModel {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private PhoneNumber phoneNumber;
  private String profilePicture;
  private Gender gender;
  private String country;
  private String address;
  private LocalDate dateOfBirth;

  private NotificationPreference notificationPreference;

  public String getFullName() {
    return capitalize(firstName) + " " + capitalize(lastName);
  }
}
