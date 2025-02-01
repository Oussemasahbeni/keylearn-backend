package com.oussema.keylearn.shared.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class NotBlankEnumValidator implements ConstraintValidator<NotBlankEnum, Enum<?>> {

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    if (value == null) {
      return false; // Value cannot be null
    }
    // Check if the enum name is blank or empty
    return StringUtils.isNotBlank(value.name());
  }
}
