// package com.oussema.keylearn.users.infrastructure.controller;
//
// import static com.inspark.sabeel.utils.AuthUtils.getCurrentAuthenticatedUserId;
//
// import com.inspark.sabeel.auth.domain.enums.RoleType;
// import com.inspark.sabeel.shared.pagination.CustomPage;
// import com.inspark.sabeel.shared.pagination.PageMapper;
// import com.inspark.sabeel.shared.pagination.PaginationUtils;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.domain.port.input.AdminUseCases;
// import com.inspark.sabeel.users.infrastructure.dto.request.AddAdminRequest;
// import com.inspark.sabeel.users.infrastructure.dto.response.AdminDto;
// import com.inspark.sabeel.users.infrastructure.dto.response.UserDto;
// import com.inspark.sabeel.users.infrastructure.mapper.UserMapper;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.mail.MessagingException;
// import java.util.UUID;
// import lombok.RequiredArgsConstructor;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequestMapping("/api/v1/admin/users")
// @RequiredArgsConstructor
// @Tag(
//    name = "Admin",
//    description = "Operations related to managing users, including retrieving all users.")
// public class AdminController {
//
//  private final AdminUseCases adminUseCases;
//  private final UserMapper userMapper;
//
//  @Operation(
//      summary = "Find all users",
//      description =
//          "Retrieve all users with pagination, sorting, and filtering options. Supports sorting by
// fields and filtering based on criteria.")
//  @GetMapping("/all")
//  public ResponseEntity<CustomPage<UserDto>> findAll(
//      @RequestParam(defaultValue = "0", required = false) int page,
//      @RequestParam(defaultValue = "10", required = false) int size,
//      @RequestParam(defaultValue = "id", required = false) String sort,
//      @RequestParam(defaultValue = "", required = false) String criteria,
//      @RequestParam(defaultValue = "ASC") String sortDirection,
//      @RequestParam(required = false) RoleType role) {
//    Pageable pageable = PaginationUtils.createPageable(page, size, sort, sortDirection);
//
//    Page<UserDto> usersPage =
//        adminUseCases
//            .findAllWithPaginationAndFiltering(criteria, role, pageable)
//            .map(userMapper::toUserDto);
//    return ResponseEntity.ok(PageMapper.toCustomPage(usersPage));
//  }
//
//  @PostMapping
//  public ResponseEntity<AdminDto> addAdmin(@RequestBody AddAdminRequest addAdminRequest)
//      throws MessagingException {
//    User userToAdd = userMapper.toUser(addAdminRequest);
//    User addedUser = adminUseCases.addAdmin(userToAdd);
//    return ResponseEntity.ok(userMapper.toAdminDto(addedUser));
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Boolean> deleteAdmin(@PathVariable UUID id) {
//    UUID currentUserId = getCurrentAuthenticatedUserId();
//
//    return new ResponseEntity<>(adminUseCases.deleteAdminById(id, currentUserId), HttpStatus.OK);
//  }
//
//  @Operation(
//      summary = "Toggle account enabled",
//      description = "Enable or disable a user account by its ID.")
//  @PatchMapping("/toggle-enabled/{id}")
//  public ResponseEntity<Boolean> toggleEnabled(@PathVariable UUID id) {
//    return new ResponseEntity<>(adminUseCases.toggleAccountEnabled(id), HttpStatus.OK);
//  }
//
//  @Operation(
//      summary = "Toggle account locked",
//      description = "Lock or unlock a user account by its ID.")
//  @PatchMapping("/toggle-locked/{id}")
//  public ResponseEntity<Boolean> toggleLocked(@PathVariable UUID id) {
//    return new ResponseEntity<>(adminUseCases.toggleAccountLocked(id), HttpStatus.OK);
//  }
// }
