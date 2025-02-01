package com.oussema.keylearn.shared.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidUrlValidator implements ConstraintValidator<ValidUrl, String> {

  private static final String URL_PATTERN =
      "^(https?://)?(www\\.)?([a-zA-Z0-9@:%._+~#=]{2,256}\\.[a-z]{2,6}\\b)([-a-zA-Z0-9@:%_+.~#?&/=]*)$";

  private final Pattern pattern = Pattern.compile(URL_PATTERN);

  @Override
  public boolean isValid(String url, ConstraintValidatorContext context) {
    return url != null && !url.trim().isEmpty() && pattern.matcher(url).matches();
  }
}
