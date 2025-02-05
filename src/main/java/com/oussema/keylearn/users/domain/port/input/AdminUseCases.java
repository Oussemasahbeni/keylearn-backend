// package com.oussema.keylearn.users.domain.port.input;
//
// import com.inspark.sabeel.auth.domain.enums.RoleType;
// import com.inspark.sabeel.users.domain.model.User;
// import jakarta.mail.MessagingException;
// import java.util.UUID;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
//
/// ** Interface defining the use cases for admin operations. */
// public interface AdminUseCases {
//
//  /**
//   * Finds all users with pagination and optional filtering by criteria and role type.
//   *
//   * @param criteria the search criteria
//   * @param role the role type to filter by
//   * @param pageable the pagination information
//   * @return a paginated list of users matching the criteria and role type
//   */
//  Page<User> findAllWithPaginationAndFiltering(String criteria, RoleType role, Pageable pageable);
//
//  /**
//   * Adds a new admin user.
//   *
//   * @param user the user to be added as an admin
//   * @return the added admin user
//   * @throws MessagingException if an error occurs while sending a notification
//   */
//  User addAdmin(User user) throws MessagingException;
//
//  Boolean deleteAdminById(UUID id, UUID currentUserId);
//
//  /**
//   * Toggles the locked status of the user account with the provided user ID.
//   *
//   * @param id the ID of the user
//   * @return true if the account is now locked, false otherwise
//   */
//  Boolean toggleAccountLocked(UUID id);
//
//  /**
//   * Toggles the enabled status of the user account with the provided user ID.
//   *
//   * @param id the ID of the user
//   * @return true if the account is now enabled, false otherwise
//   */
//  Boolean toggleAccountEnabled(UUID id);
// }
