package it.xpug.kata.birthdaygreetings.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmployeeFileRuntimeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("file name present", new EmployeeFileRuntimeException("file.txt").getMessage(), "Error for file: 'file.txt'");
        assertEquals("file name null", new EmployeeFileRuntimeException(null).getMessage(), "Error for file: 'null'");
        assertEquals("file name null", new EmployeeFileRuntimeException("").getMessage(), "Error for file: ''");

        assertEquals("file name present", new EmployeeFileRuntimeException("file.txt", null).getMessage(), "Error for file: 'file.txt'");
        assertEquals("file name null", new EmployeeFileRuntimeException(null, null).getMessage(), "Error for file: 'null'");
        assertEquals("file name null", new EmployeeFileRuntimeException("", null).getMessage(), "Error for file: ''");
    }
}
