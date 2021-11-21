package it.xpug.kata.birthdaygreetings.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NotificationExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("equals message", new NotificationRuntimeException("message!").getMessage(), "message!");
        assertNull("null message", new NotificationRuntimeException(null).getMessage());
    }
}
