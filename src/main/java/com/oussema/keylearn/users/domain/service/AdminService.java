// package com.oussema.keylearn.users.domain.service;
//
// import static com.inspark.sabeel.notifications.domain.enums.EmailType.SET_PASSWORD;
// import static com.inspark.sabeel.shared.Constants.CODE_LENGTH;
// import static com.inspark.sabeel.utils.DateUtils.convertDaysToSeconds;
// import static com.inspark.sabeel.utils.Generators.generateRandomCode;
// import static java.time.Instant.now;
//
// import com.inspark.sabeel.auth.domain.enums.CodeStatus;
// import com.inspark.sabeel.auth.domain.enums.CodeType;
// import com.inspark.sabeel.auth.domain.enums.RoleType;
// import com.inspark.sabeel.auth.domain.model.AuthCode;
// import com.inspark.sabeel.auth.domain.port.output.Codes;
// import com.inspark.sabeel.exception.ForbiddenException;
// import com.inspark.sabeel.exception.NotFoundException;
// import com.inspark.sabeel.notifications.domain.port.output.Emails;
// import com.inspark.sabeel.shared.annotation.DomainService;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.domain.port.input.AdminUseCases;
// import com.inspark.sabeel.users.domain.port.output.Admins;
// import com.inspark.sabeel.users.domain.port.output.Users;
// import jakarta.mail.MessagingException;
// import java.util.Map;
// import java.util.UUID;
// import lombok.RequiredArgsConstructor;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
//
// @DomainService
// @RequiredArgsConstructor
// public class AdminService implements AdminUseCases {
//
//  private final Admins admins;
//  private final Emails emails;
//  private final Users users;
//  private final Codes codes;
//
//  @Override
//  public Page<User> findAllWithPaginationAndFiltering(
//      String criteria, RoleType role, Pageable pageable) {
//
//    return admins.findAll(criteria, pageable, role);
//  }
//
//  @Override
//  public User addAdmin(User user) throws MessagingException {
//
//    user.setEnabled(true);
//    user.setEmailVerified(true);
//
//    var code = generateRandomCode(CODE_LENGTH);
//    User admin = admins.save(user);
//    AuthCode authCodeToSave =
//        AuthCode.builder()
//            .code(code)
//            .status(CodeStatus.PENDING)
//            .type(CodeType.SET_PASSWORD)
//            .email(admin.getEmail())
//            .expireAt(now().plusSeconds(convertDaysToSeconds(2)))
//            .userId(admin.getId())
//            .build();
//    codes.save(authCodeToSave);
//    Map<String, Object> properties =
//        Map.of(
//            "email", user.getEmail(),
//            "fullName", user.getFullName(),
//            "authCode", code,
//            "url", SET_PASSWORD.getFullPath());
//    emails.sendEmail(user.getEmail(), SET_PASSWORD, properties);
//    return admin;
//  }
//
//  @Override
//  public Boolean toggleAccountLocked(UUID id) {
//
//    try {
//      User user = checkUserById(id);
//      boolean result = !user.isAccountLocked();
//      user.setAccountLocked(result);
//      users.update(user);
//      return true;
//    } catch (Exception e) {
//      return false;
//    }
//  }
//
//  @Override
//  public Boolean toggleAccountEnabled(UUID id) {
//
//    try {
//      User user = checkUserById(id);
//      boolean result = !user.isEnabled();
//      user.setEnabled(result);
//      users.update(user);
//      return true;
//    } catch (Exception e) {
//      return false;
//    }
//  }
//
//  @Override
//  public Boolean deleteAdminById(UUID id, UUID currentUserId) {
//    if (currentUserId.equals(id)) {
//      throw new ForbiddenException(ForbiddenException.ForbiddenExceptionType.ACTION_NOT_ALLOWED);
//    }
//    return admins.deleteById(id);
//  }
//
//  private User checkUserById(UUID userId) {
//    return users
//        .findById(userId)
//        .orElseThrow(
//            () ->
//                new NotFoundException(
//                    NotFoundException.NotFoundExceptionType.USER_NOT_FOUND, userId));
//  }
// }
