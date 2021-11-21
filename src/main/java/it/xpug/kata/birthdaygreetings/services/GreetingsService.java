package it.xpug.kata.birthdaygreetings.services;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsException;

public interface GreetingsService<T> {
    void sendGreetings(T t) throws GreetingsException;
}
