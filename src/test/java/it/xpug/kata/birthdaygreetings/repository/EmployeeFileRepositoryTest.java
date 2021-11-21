package it.xpug.kata.birthdaygreetings.repository;

import it.xpug.kata.birthdaygreetings.beans.Employee;
import it.xpug.kata.birthdaygreetings.exceptions.EmployeeFileRuntimeException;
import it.xpug.kata.birthdaygreetings.repository.impl.EmployeeFileRepository;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class EmployeeFileRepositoryTest {

    @Test
    public void testGetEmployee() {
        EmployeeFileRepository employeeFileRepository = new EmployeeFileRepository("src/test/resources/employee_data_test.txt");

        List<Employee> employees = employeeFileRepository.getAllEmployees().collect(Collectors.toList());

        assertEquals("2 employees", employees.size(), 2);
        assertTrue(employees.stream().anyMatch(e->e.getEmail().equals("john.doe@foobar.com")));
        assertTrue(employees.stream().anyMatch(e->e.getEmail().equals("mary.ann@foobar.com")));
    }

    @Test
    public void nullValueTest() {
        EmployeeFileRuntimeException fileNameException = assertThrows(EmployeeFileRuntimeException.class, () -> new EmployeeFileRepository(null));
        assertEquals(fileNameException.getMessage(), "Error for file: 'null'");

        fileNameException = assertThrows(EmployeeFileRuntimeException.class,  () -> new EmployeeFileRepository(""));
        assertEquals(fileNameException.getMessage(), "Error for file: ''");
    }
}
