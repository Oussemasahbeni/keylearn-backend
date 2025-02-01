package com.oussema.keylearn.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/actuator/**",
            "/ws/**"
    };

  @Bean
  public SecurityFilterChain jwtAuthorizationSecurityFilterChain(HttpSecurity http)
      throws Exception {
    return http.cors(Customizer.withDefaults())
        .csrf(
            AbstractHttpConfigurer
                ::disable) // we don't need CSRF protection for JWT based authentication
        .sessionManagement(
            session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS)) // JWT doesn't need sessions
        .authorizeHttpRequests(
            request ->
                request
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest()
                    .authenticated()
            ) // Set the authentication provider
        // Add the JWT filter before the UsernamePasswordAuthenticationFilter
        .addFilterAfter(new RequestLoggingFilter(), UsernamePasswordAuthenticationFilter.class)
            .oauth2ResourceServer(
                    oauth2 ->
                            oauth2.jwt(
                                    token ->
                                            token.jwtAuthenticationConverter(
                                                    new KeyclaokJwtAuthenticationConverter())))
        .build();
  }
}
