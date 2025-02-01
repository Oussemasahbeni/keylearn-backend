package com.oussema.keylearn.shared.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public class NotEmptyCollectionValidator
    implements ConstraintValidator<NotEmptyCollection, Collection<?>> {

  @Override
  public boolean isValid(Collection<?> collection, ConstraintValidatorContext context) {
    return collection != null && !collection.isEmpty();
  }
}
