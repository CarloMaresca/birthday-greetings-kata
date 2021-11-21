package it.xpug.kata.birthdaygreetings.exceptions;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class GreetingsDateExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("equals message", new GreetingsDateException("message!").getMessage(), "message!");
        assertNull("null message", new GreetingsDateException(null).getMessage());
        assertNull("null message", new GreetingsDateException(null, new ParseException("a", 1)).getMessage());
    }
}
