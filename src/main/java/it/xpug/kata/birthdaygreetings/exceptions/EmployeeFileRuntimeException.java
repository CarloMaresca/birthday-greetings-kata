package it.xpug.kata.birthdaygreetings.exceptions;

public class EmployeeFileRuntimeException extends RuntimeException {
    public EmployeeFileRuntimeException(String fileName) {
        super(String.format("Error for file: '%s'", fileName));
    }
}
