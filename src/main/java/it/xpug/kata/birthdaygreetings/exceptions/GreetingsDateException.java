package it.xpug.kata.birthdaygreetings.exceptions;

import java.text.ParseException;

public class GreetingsDateException extends GreetingsException {
    public GreetingsDateException(String message) {
        super(message);
    }

    public GreetingsDateException(String message, ParseException e) {
        super(message, e);
    }
}
