package com.oussema.keylearn.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  // Exception handler for validation errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    log.error("Validation error: {}", ex.getMessage(), ex);

    // Create a map to hold field-specific errors
    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              fieldErrors.put(fieldName, errorMessage);
            });

    // Wrap the errors map under an "errors" key
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Validation failed");
    response.put("errors", fieldErrors);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // Exception handler for NotFoundException
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    log.error("Not found: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestPartException.class)
  public ResponseEntity<Map<String, String>> handleMissingServletRequestPartException(
      MissingServletRequestPartException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  // Exception handler for DisabledUserException
  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<Object> handleDisabledUserException(DisabledException ex) {
    log.error("User disabled: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse("error.server.user.disabled", "error.server.user.disabled.msg", ex),
        HttpStatus.FORBIDDEN);
  }

  // Exception handler for BadCredentialsException
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
    log.error("Bad credentials: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(
            "error.server.bad-credentials", "error.server.bad-credentials.msg", ex),
        HttpStatus.FORBIDDEN);
  }

  // Exception handler for LockedException
  @ExceptionHandler(LockedException.class)
  public ResponseEntity<Object> handleLockedException(LockedException ex) {
    log.error("User locked: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse("error.server.user.locked", "error.server.user.locked.msg", ex),
        HttpStatus.FORBIDDEN);
  }

  // Exception handler for ConflictException
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Object> handleConflictException(ConflictException ex) {
    log.error("Conflict: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.CONFLICT);
  }

  // Exception handler for ExistsException
  @ExceptionHandler(ExistsException.class)
  public ResponseEntity<Object> handleExistsException(ExistsException ex) {
    log.error("Exists: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.CONFLICT);
  }

  // Exception handler for BadRequestException
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
    log.error("Bad request: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.BAD_REQUEST);
  }

  // Exception handler for ForbiddenException
  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
    log.error("Forbidden: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
    log.error("Unauthorized: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
    log.error("Access denied: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse("Access denied", "error.server.access.denied.msg", ex),
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(GenericException.class)
  public ResponseEntity<Object> handleGenericException(GenericException ex) {
    log.error("Unexpected error: {}", ex.getMessage(), ex);
    return buildResponseEntity(
        new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Private method to build ResponseEntity
  private ResponseEntity<Object> buildResponseEntity(Object body, HttpStatus status) {
    return new ResponseEntity<>(body, status);
  }
}
