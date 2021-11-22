package it.xpug.kata.birthdaygreetings.beans;

public interface NotificationMessage {
    String getFrom();

    String getRecipient();

    String getBody();

    String getSubject();
}
