package com.oussema.keylearn.utils;

import com.oussema.keylearn.exception.UnauthorizedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.UUID;

public class AuthUtils {

  private AuthUtils() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static UUID getCurrentAuthenticatedUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!isAuthenticated()) {
      throw new UnauthorizedException(
          UnauthorizedException.UnauthorizedExceptionType.USER_NOT_AUTHORIZED);
    }
    return UUID.fromString(authentication.getName());
  }

  public static UUID getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return null;
    }
    return UUID.fromString(authentication.getName());
  }

  public static Collection<? extends GrantedAuthority> getUserAuthorities() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getAuthorities().isEmpty()) {
      return null;
    }
    return authentication.getAuthorities();
  }

  public static boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null
        && authentication.isAuthenticated()
        && !(authentication instanceof AnonymousAuthenticationToken);
  }
}
