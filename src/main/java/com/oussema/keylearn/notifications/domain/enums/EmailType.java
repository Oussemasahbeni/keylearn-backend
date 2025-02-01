package com.oussema.keylearn.notifications.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum EmailType {
  ACCOUNT_CONFIRMATION("Account Confirmation", "confirmationAccount", "/activate-account"),
  RESET_PASSWORD("Password Reset", "resetPassword", "/reset-password"),
  SET_PASSWORD("Set Password", "setPassword", "/set-password"),
  NEWS_LETTER("News Letter", "newsLetter", "/"),
  NEWS_LETTER_CONFIRMATION(
      "News Letter Confirmation", "newsLetterConfirmation", "/newsletter/confirm-subscription"),
  POST_FLAGGED("Blog Post Report", "blogPostReport", "/saha-blog/post/"),
  RESOURCE_ADDED("Resource Added", "resourceAdded", "/admin/resources"),
  SCRAPING_COMPLETE("Web Scraping Complete", "scrapingComplete", "/admin/articles/web-scraped");

  @Setter private static String frontendUrl;
  private final String subject;
  private final String templateName;
  private final String path;

  public String getFullPath() {
    return frontendUrl + path;
  }
}
