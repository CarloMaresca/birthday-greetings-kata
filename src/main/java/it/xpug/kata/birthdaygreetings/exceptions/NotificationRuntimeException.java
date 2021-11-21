package it.xpug.kata.birthdaygreetings.exceptions;

public class NotificationRuntimeException extends RuntimeException {
    public NotificationRuntimeException(String message) {
        super(message);
    }
}
