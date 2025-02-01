package com.oussema.keylearn.utils;

import java.security.SecureRandom;

public class Generators {

  private Generators() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static String generateRandomCode(int length) {
    String characters = "0123456789";
    StringBuilder codeBuilder = new StringBuilder();
    SecureRandom random = new SecureRandom();
    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(characters.length());
      codeBuilder.append(characters.charAt(randomIndex));
    }
    return codeBuilder.toString();
  }
}
