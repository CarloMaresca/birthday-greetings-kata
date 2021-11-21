package it.xpug.kata.birthdaygreetings.repository;

import it.xpug.kata.birthdaygreetings.beans.Employee;

import java.util.stream.Stream;

public interface EmployeeRepository {
    Stream<Employee> getAllEmployees();
}
