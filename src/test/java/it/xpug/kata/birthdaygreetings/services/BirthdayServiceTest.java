package it.xpug.kata.birthdaygreetings.services;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthdaygreetings.exceptions.EmployeeFileRuntimeException;
import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import it.xpug.kata.birthdaygreetings.repository.impl.EmployeeFileRepository;
import it.xpug.kata.birthdaygreetings.services.impl.BirthdayService;
import it.xpug.kata.birthdaygreetings.services.impl.MailService;
import it.xpug.kata.birthdaygreetings.utils.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class BirthdayServiceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private SimpleSmtpServer mailServer;
	private MailService mailService;
	private EmployeeFileRepository employeeFileRepository;

	@Before
	public void setUp() throws IOException {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		mailService = new MailService("localhost", NONSTANDARD_PORT);
		employeeFileRepository = new EmployeeFileRepository("src/test/resources/employee_data_test.txt");
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetingsWhenItsSomebodysBirthday() throws Exception {
		GreetingsService<Date> greetingsService = new BirthdayService(mailService, employeeFileRepository);
		greetingsService.sendGreetings(DateUtils.stringToDate("2008/10/08"));

		assertEquals("message sent", 1, mailServer.getReceivedEmails().size());
		SmtpMessage message = mailServer.getReceivedEmails().get(0);
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		List<String> recipients = message.getHeaderValues(Message.RecipientType.TO.toString());
		assertEquals(1, recipients.size());
		assertEquals("john.doe@foobar.com", recipients.get(0));
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		GreetingsService<Date> greetingsService = new BirthdayService(mailService, employeeFileRepository);
		greetingsService.sendGreetings(DateUtils.stringToDate("2008/01/01"));

		assertEquals("message sent", 0, mailServer.getReceivedEmails().size());
	}

	@Test
	public void fileNotFoundThrowBirthdayGreetingsException() {
		GreetingsService<Date> greetingsService = new BirthdayService(mailService, new EmployeeFileRepository("file_not_found.txt"));
		assertThrows(EmployeeFileRuntimeException.class, ()-> greetingsService.sendGreetings(DateUtils.stringToDate("2008/10/08")));
	}

	@Test
	public void nullValueTest() {
		GreetingsService<Date> greetingsService = new BirthdayService(mailService, employeeFileRepository);
		assertThrows(GreetingsDateException.class, ()-> greetingsService.sendGreetings(null));
	}
}
