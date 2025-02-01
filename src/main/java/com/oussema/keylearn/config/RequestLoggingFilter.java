package com.oussema.keylearn.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
public class RequestLoggingFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    long startTime = System.currentTimeMillis();
    try {
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.error("Exception occurred during request processing: {}", e.getMessage(), e);
      throw e;
    }

    long duration = System.currentTimeMillis() - startTime;
    String logInfo =
        String.format(
            "Request: %s %s, Response status: %d, duration : %d ms",
            request.getMethod(), request.getRequestURL(), response.getStatus(), duration);

    // Log response information
    log.info(logInfo);
  }
}
