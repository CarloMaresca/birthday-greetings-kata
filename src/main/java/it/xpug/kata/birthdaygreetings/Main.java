package it.xpug.kata.birthdaygreetings;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsException;
import it.xpug.kata.birthdaygreetings.repository.impl.EmployeeFileRepository;
import it.xpug.kata.birthdaygreetings.services.GreetingsService;
import it.xpug.kata.birthdaygreetings.services.impl.BirthdayService;
import it.xpug.kata.birthdaygreetings.services.impl.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws GreetingsException {
		log.info("start");

		GreetingsService<Date> service = new BirthdayService(
				new MailService("localhost", 25),
				new EmployeeFileRepository("employee_data.txt")
		);

		service.sendGreetings(new Date());
	}
}
