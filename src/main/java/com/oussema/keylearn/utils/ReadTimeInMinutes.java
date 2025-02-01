package com.oussema.keylearn.utils;

import java.util.Arrays;

/**
 * Utility class for calculating the estimated read time of a given text in minutes. The calculation
 * is based on the average reading speed, sentence length, and syllable count. Inspired From: <a
 * href="https://dpericich.medium.com/writing-an-algorithm-to-calculate-article-read-length-b45181f16a79">...</a>
 *
 * <p>Overall Algorithm Summary
 *
 * <p>Input Validation: Ensures that the input text is neither null nor empty.
 *
 * <p>Word Count: Removes specific punctuation and splits the text into words based on whitespace to
 * count the total number of words.
 *
 * <p>Sentence Count and Average Sentence Length: Splits the text into sentences based on
 * sentence-ending punctuation and calculates the average number of words per sentence.
 *
 * <p>Syllable Count: Counts the total number of syllables in the text by analyzing each word.
 * Calculates the average number of syllables per word.
 *
 * <p>Difficulty Multiplier: Computes a multiplier based on the average syllables per word and the
 * average sentence length. This multiplier adjusts the reading speed to account for text
 * complexity.
 *
 * <p>Adjusted Reading Speed: Divides the average reading speed by the difficulty multiplier to get
 * an adjusted speed.
 *
 * <p>Read Time Calculation: Divides the total word count by the adjusted reading speed and rounds
 * up to estimate the read time in minutes.
 */
public class ReadTimeInMinutes {

  /** Average number of words a person can read per minute. */
  public static final int AVERAGE_WORDS_PER_MINUTE = 238;

  /**
   * Base number of words per sentence used for calculating sentence length multiplier. The average
   * sentence length in English is 21 words.
   */
  public static final int BASE_SENTENCE_WORD_COUNT = 21;

  /**
   * Base number of syllables used for calculating syllable multiplier. For example: The word "cat"
   * has one syllable. The word "water" has two syllables: wa and ter. The word "elephant" has three
   * syllables: el, e, and phant.
   */
  public static final double BASE_SYLLABLE_COUNT = 1.5;

  /**
   * Calculates the difficulty multiplier based on average syllable count and average sentence
   * length.
   *
   * @param averageSyllable the average number of syllables per word
   * @param averageSentenceLength the average number of words per sentence
   * @return the difficulty multiplier
   */
  public static double calculateDifficultyMultiplier(
      double averageSyllable, double averageSentenceLength) {
    double syllableMultiplier = calculateSyllableMultiplier(averageSyllable);
    double sentenceLengthMultiplier = calculateSentenceLengthMultiplier(averageSentenceLength);
    return 1.0 + syllableMultiplier + sentenceLengthMultiplier;
  }

  /**
   * Calculates the syllable multiplier based on the average syllable count.
   *
   * @param averageSyllable the average number of syllables per word
   * @return the syllable multiplier
   */
  public static double calculateSyllableMultiplier(double averageSyllable) {
    if (averageSyllable > BASE_SYLLABLE_COUNT) {
      return (averageSyllable - BASE_SYLLABLE_COUNT) * 0.05;
    } else {
      return 0;
    }
  }

  /**
   * Calculates the sentence length multiplier based on the average sentence length.
   *
   * @param averageSentenceLength the average number of words per sentence
   * @return the sentence length multiplier
   */
  public static double calculateSentenceLengthMultiplier(double averageSentenceLength) {
    if (averageSentenceLength > BASE_SENTENCE_WORD_COUNT) {
      return (averageSentenceLength - BASE_SENTENCE_WORD_COUNT) * 0.05;
    } else {
      return 0;
    }
  }

  /**
   * Counts the number of syllables in a word.
   *
   * @param word the word to count syllables for
   * @return the number of syllables
   */
  public static int countSyllables(String word) {
    word = word.toLowerCase().replaceAll("[^a-z]", "");
    if (word.isEmpty()) return 0;

    int syllableCount = 0;
    boolean prevVowel = false;
    String vowels = "aeiouy";

    for (char c : word.toCharArray()) {
      boolean isVowel = vowels.indexOf(c) >= 0;
      if (isVowel && !prevVowel) {
        syllableCount++;
      }
      prevVowel = isVowel;
    }

    // Subtract silent 'e'
    if (word.endsWith("e") && syllableCount > 1) {
      syllableCount--;
    }

    return syllableCount;
  }

  /**
   * Calculates the time required to read a given text.
   *
   * @param text the text to calculate the read time for
   * @return the read time in minutes
   */
  public static int calculateReadTime(String text) {
    if (text == null || text.trim().isEmpty()) {
      return 0;
    }

    // Remove punctuation for word count
    String textWithoutDivisionPunctuation = text.replaceAll("[;:,.!?]", "");
    String[] words = textWithoutDivisionPunctuation.split("\\s+");
    int wordCount = words.length;

    // Calculate sentence count and average sentence length
    String[] textSentences = text.split("[.!?]+\\s*");
    int sentenceCount =
        textSentences.length > 0 ? textSentences.length : 1; // Avoid division by zero
    double averageSentenceLength = (double) wordCount / sentenceCount;

    // Syllable count
    int syllableCount = Arrays.stream(words).mapToInt(ReadTimeInMinutes::countSyllables).sum();
    double averageSyllableCount =
        syllableCount > 0 ? (double) syllableCount / wordCount : BASE_SYLLABLE_COUNT;

    // Calculate difficulty multiplier
    double difficultyMultiplier =
        calculateDifficultyMultiplier(averageSyllableCount, averageSentenceLength);

    // Calculate reading speed
    double adjustedReadingSpeed = AVERAGE_WORDS_PER_MINUTE / difficultyMultiplier;

    return (int) Math.ceil(wordCount / adjustedReadingSpeed);
  }
}
