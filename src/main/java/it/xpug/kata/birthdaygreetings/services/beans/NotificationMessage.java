package it.xpug.kata.birthdaygreetings.services.beans;

public interface NotificationMessage {
    String getFrom();

    String getRecipient();

    String getBody();

    String getSubject();
}
