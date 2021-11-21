package it.xpug.kata.birthdaygreetings.services.impl;

import it.xpug.kata.birthdaygreetings.beans.BirthdayMessage;
import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import it.xpug.kata.birthdaygreetings.exceptions.GreetingsException;
import it.xpug.kata.birthdaygreetings.repository.EmployeeRepository;
import it.xpug.kata.birthdaygreetings.services.GreetingsService;
import it.xpug.kata.birthdaygreetings.services.NotificationService;

import java.util.Date;
import java.util.Objects;

public class BirthdayService implements GreetingsService<Date> {

	private final NotificationService notificationService;
	private EmployeeRepository employeeRepository;

	public BirthdayService(NotificationService notificationService, EmployeeRepository employeeRepository) {
		this.notificationService = notificationService;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void sendGreetings(Date date) throws GreetingsException {
		if(Objects.isNull(date)) throw new GreetingsDateException("date is required");

		employeeRepository.getAllEmployees()
				.filter(employee -> employee.isBirthday(date))
				.map(BirthdayMessage::new)
				.forEach(notificationService::sendMessage);
	}
}
