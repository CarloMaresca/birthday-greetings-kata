package it.xpug.kata.birthdaygreetings.exceptions;

import java.io.IOException;

public class EmployeeFileRuntimeException extends RuntimeException {
    public EmployeeFileRuntimeException(String fileName, IOException e) {
        super(String.format("Error for file: '%s'", fileName), e);
    }

    public EmployeeFileRuntimeException(String fileName) {
        this(fileName, null);
    }
}
