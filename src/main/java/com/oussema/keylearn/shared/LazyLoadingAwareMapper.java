package com.oussema.keylearn.shared;

import org.hibernate.Hibernate;

import java.util.Collection;

public interface LazyLoadingAwareMapper {
  default boolean isNotLazyLoaded(Collection<?> sourceCollection) {
    // Case: Source field in domain object is lazy: Skip mapping
    // Continue Mapping
    return Hibernate.isInitialized(sourceCollection);

    // Skip mapping
  }
}
