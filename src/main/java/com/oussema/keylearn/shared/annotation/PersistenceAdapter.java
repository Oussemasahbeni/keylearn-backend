package com.oussema.keylearn.shared.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a class as a persistence adapter in the application.
 *
 * <p>This annotation is a custom stereotype annotation that combines the Spring {@link Service}
 * annotation with a runtime retention policy. It is used to indicate that an annotated class is a
 * persistence adapter, which typically interacts with the database or other persistence mechanisms.
 *
 * <p>Usage example:
 *
 * <pre>
 * &#64;PersistenceAdapter
 * public class MyPersistenceAdapter {
 *     // persistence logic methods
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
public @interface PersistenceAdapter {}
