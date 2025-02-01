package com.oussema.keylearn.utils;

import java.util.Random;

public class RandomContentGenerator {
  private static final String[] WORDS = {
    "technology",
    "innovation",
    "global",
    "future",
    "research",
    "science",
    "development",
    "education",
    "growth",
    "exploration",
    "creativity",
    "solutions",
    "engineering",
    "design",
    "impact",
    "collaboration",
    "artificial",
    "intelligence",
    "sustainability",
    "vision",
    "challenges",
    "industry",
    "society",
    "humanity",
    "progress",
    "energy",
    "digital",
    "platform",
    "evolution",
    "opportunity",
    "transformation"
  };

  private static final String[] CONNECTORS = {
    "Moreover",
    "Therefore",
    "However",
    "In addition",
    "As a result",
    "On the other hand",
    "For instance"
  };

  private static final String[] ADJECTIVES = {
    "revolutionary",
    "innovative",
    "rapid",
    "sustainable",
    "unprecedented",
    "dynamic",
    "global",
    "visionary",
    "complex",
    "digital",
    "transformative",
    "strategic",
    "cutting-edge"
  };

  private static final String[] VERBS = {
    "drives",
    "shapes",
    "advances",
    "influences",
    "supports",
    "creates",
    "enhances",
    "improves",
    "empowers",
    "redefines",
    "enables",
    "propels"
  };

  private static final String[] SUBJECTS = {
    "The industry",
    "Research",
    "Technological advancement",
    "Innovation",
    "Education",
    "Sustainability",
    "Artificial intelligence",
    "Global collaboration",
    "Digital transformation",
    "Scientific exploration"
  };

  private static final Random random = new Random();

  // Generate a random sentence with a specific structure
  private static String generateRandomSentence() {
    String subject = SUBJECTS[random.nextInt(SUBJECTS.length)];
    String verb = VERBS[random.nextInt(VERBS.length)];
    String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
    String word1 = WORDS[random.nextInt(WORDS.length)];
    String word2 = WORDS[random.nextInt(WORDS.length)];
    String connector = CONNECTORS[random.nextInt(CONNECTORS.length)];

    // Structure sentence patterns for uniqueness
    String[] sentenceStructures = {
      subject
          + " "
          + verb
          + " "
          + "a "
          + adjective
          + " "
          + word1
          + " in the field of "
          + word2
          + ".",
      "In today's world, "
          + word1
          + " plays a crucial role in "
          + verb
          + " the "
          + adjective
          + " "
          + word2
          + ".",
      connector + ", " + subject + " focuses on " + word1 + " to achieve " + adjective + " results."
    };

    return sentenceStructures[random.nextInt(sentenceStructures.length)];
  }

  // Generate a random paragraph with a specified sentence count
  private static String generateRandomParagraph(int sentenceCount) {
    StringBuilder paragraph = new StringBuilder();
    for (int i = 0; i < sentenceCount; i++) {
      paragraph.append(generateRandomSentence()).append(" ");
    }
    return paragraph.toString().trim();
  }

  // Generate unique random content consisting of multiple paragraphs
  public static String generateUniqueContent(int paragraphCount, int sentencesPerParagraph) {
    StringBuilder content = new StringBuilder();
    for (int i = 0; i < paragraphCount; i++) {
      content
          .append(generateRandomParagraph(sentencesPerParagraph))
          .append("\n\n"); // Double newline for paragraph separation
    }
    return content.toString();
  }
}
