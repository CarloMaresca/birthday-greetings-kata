package it.xpug.kata.birthdaygreetings.services;

import it.xpug.kata.birthdaygreetings.beans.NotificationMessage;

public interface NotificationService {
    void sendMessage(NotificationMessage mailMessage);
}
