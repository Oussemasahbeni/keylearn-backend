package com.oussema.keylearn.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateUtils {

  private DateUtils() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static Optional<ZonedDateTime> convertToZoneDateTime(String dateStr) {
    try {
      return Optional.of(ZonedDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME));
    } catch (DateTimeParseException e) {
      return Optional.empty();
    }
  }

  public static long convertHoursToSeconds(long hours) {
    return Duration.ofHours(hours).toSeconds();
  }

  public static long convertMinutesToSeconds(long minutes) {
    return Duration.ofMinutes(minutes).toSeconds();
  }

  public static long convertDaysToSeconds(long days) {
    return Duration.ofDays(days).toSeconds();
  }

  /**
   * Checks if the given Instant is in the past compared to the current time.
   *
   * @param time the Instant to check
   * @return true if the time is in the past, false otherwise
   */
  public static boolean isInPast(Instant time) {
    return Instant.now().isAfter(time);
  }

  /**
   * Calculates the duration between two Instants.
   *
   * @param start the start Instant
   * @param end the end Instant
   * @return the duration between start and end
   */
  public static Duration calculateDuration(Instant start, Instant end) {
    return Duration.between(start, end);
  }
}
