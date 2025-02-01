package com.oussema.keylearn.shared.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a class as a domain service in the application.
 *
 * <p>This annotation is a custom stereotype annotation that combines the Spring {@link Service}
 * annotation with a runtime retention policy. It is used to indicate that an annotated class is a
 * domain service, which typically contains business logic and interacts with domain entities.
 *
 * <p>Usage example:
 *
 * <pre>
 * &#64;DomainService
 * public class MyDomainService {
 *     // business logic methods
 * }
 * </pre>
 *
 * <p>By using this annotation, the annotated class will be automatically detected and registered as
 * a Spring bean.
 *
 * @see Service
 * @see Retention
 * @see RetentionPolicy
 */
@Service
@Target(TYPE)
@Retention(RUNTIME)
public @interface DomainService {}
