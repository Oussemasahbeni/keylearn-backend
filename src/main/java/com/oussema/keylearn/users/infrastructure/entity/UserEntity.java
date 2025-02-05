package com.oussema.keylearn.users.infrastructure.entity;

import static jakarta.persistence.EnumType.STRING;
import static org.springframework.util.StringUtils.capitalize;

import com.oussema.keylearn.shared.AbstractAuditingEntity;
import com.oussema.keylearn.users.domain.enums.Gender;
import com.oussema.keylearn.users.domain.enums.NotificationPreference;
import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditingEntity {

  @Id private UUID id;

  @Column(name = "firstName", nullable = false)
  private String firstName;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phoneNumber")
  @Embedded
  private PhoneNumber phoneNumber;

  @Column(name = "profilePicture")
  private String profilePicture;

  @Column(name = "gender")
  @Enumerated(STRING)
  private Gender gender;

  @Column(name = "notification_preference")
  @Enumerated(STRING)
  private NotificationPreference notificationPreference;

  @Column(name = "country")
  private String country;

  @Column(name = "dateOfBirth")
  private LocalDate dateOfBirth;

  @Column(name = "address")
  private String address;

  public String getFullName() {
    return capitalize(firstName) + " " + capitalize(lastName);
  }
}
