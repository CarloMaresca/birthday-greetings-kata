package it.xpug.kata.birthdaygreetings.repository.impl;

import it.xpug.kata.birthdaygreetings.beans.Employee;
import it.xpug.kata.birthdaygreetings.exceptions.EmployeeFileRuntimeException;
import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import it.xpug.kata.birthdaygreetings.repository.EmployeeRepository;
import it.xpug.kata.birthdaygreetings.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class EmployeeFileRepository implements EmployeeRepository {
    private static final Logger log = LoggerFactory.getLogger(EmployeeFileRepository.class);

    private String fileName;

    public EmployeeFileRepository(String fileName) {
        if(StringUtils.isEmpty(fileName)) throw new EmployeeFileRuntimeException(fileName);
        this.fileName = fileName;
    }

    @Override
    public Stream<Employee> getAllEmployees() {
        try {
            return Files.lines(Paths.get(fileName))
                    .skip(1)
                    .map(line -> line.split(", "))
                    .map(this::createEmployee)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        } catch (IOException e) {
            throw new EmployeeFileRuntimeException(fileName);
        }
    }

    private Optional<Employee> createEmployee(String[] employeeData) {
        try {
            return Optional.of(new Employee(employeeData[1], employeeData[0], DateUtils.stringToDate(employeeData[2]), employeeData[3]));
        } catch (GreetingsDateException e) {
            log.error("error during createEmployee", e);
            return Optional.empty();
        }
    }
}
