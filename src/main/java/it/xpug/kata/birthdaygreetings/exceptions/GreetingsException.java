package it.xpug.kata.birthdaygreetings.exceptions;

public class GreetingsException extends Exception {
    public GreetingsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GreetingsException(String message) {
        super(message);
    }
}
