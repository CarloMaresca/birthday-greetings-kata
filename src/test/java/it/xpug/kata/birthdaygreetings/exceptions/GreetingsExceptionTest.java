package it.xpug.kata.birthdaygreetings.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GreetingsExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("equals message", new GreetingsException("message!").getMessage(), "message!");
        assertNull("null message", new GreetingsException(null).getMessage());
    }
}
