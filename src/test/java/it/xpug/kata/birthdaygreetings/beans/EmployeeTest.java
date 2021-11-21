package it.xpug.kata.birthdaygreetings.beans;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import it.xpug.kata.birthdaygreetings.utils.DateUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EmployeeTest {

	@Test
	public void testBirthday() throws Exception {
		Employee employee = new Employee("foo", "bar", DateUtils.stringToDate("1990/01/31"), "a@b.c");

		assertFalse("not his birthday", employee.isBirthday(DateUtils.stringToDate("2008/01/30")));
		assertTrue("his birthday", employee.isBirthday(DateUtils.stringToDate("2008/01/31")));
	}

	@Test
	public void equalityAndHashCode() throws Exception {
		Employee paperon = new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("1999/09/01"), "paperon@depaperoni.com");
		Employee paperonClone = new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("1999/09/01"), "paperon@depaperoni.com");
		Employee paperino = new Employee("Paperino", "Paolino", DateUtils.stringToDate("1999/09/01"), "paperino@paolino.com");

		assertEquals("test equals", paperon, paperonClone);
		assertEquals("test equals hash code", paperon.hashCode(), paperonClone.hashCode());

		assertNotEquals("test not equals", paperon, paperino);
		assertNotEquals("test not equals hash code", paperon.hashCode(), paperino.hashCode());


		Employee paperonSimilar = new Employee("Xaperon", "De' Paperoni", DateUtils.stringToDate("1999/09/01"), "paperon@depaperoni.com");
		assertNotEquals("first name not equals", paperon, paperonSimilar);
		assertNotEquals("test not equals hash code", paperon.hashCode(), paperonSimilar.hashCode());

		paperonSimilar = new Employee("Paperon", "De' XXperoni", DateUtils.stringToDate("1999/09/01"), "paperon@depaperoni.com");
		assertNotEquals("last name not equals", paperon, paperonSimilar);
		assertNotEquals("test not equals hash code", paperon.hashCode(), paperonSimilar.hashCode());

		paperonSimilar = new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("1999/08/01"), "paperon@depaperoni.com");
		assertNotEquals("birth date not equals", paperon, paperonSimilar);
		assertNotEquals("test not equals hash code", paperon.hashCode(), paperonSimilar.hashCode());

		paperonSimilar = new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("1999/08/01"), "papXXon@depaperoni.com");
		assertNotEquals("email not equals", paperon, paperonSimilar);
		assertNotEquals("test not equals hash code", paperon.hashCode(), paperonSimilar.hashCode());
	}

	@Test
	public void testOtherGetter() throws GreetingsDateException {
		Employee paperon = new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("1999/09/01"), "paperon@depaperoni.com");
		assertEquals("test email", paperon.getEmail(), "paperon@depaperoni.com");
		assertEquals("test first name", paperon.getFirstName(), "Paperon");
		assertEquals("test last name", paperon.getLastName(), "De' Paperoni");
		assertEquals("test birth date", paperon.getBirthDate(), DateUtils.stringToDate("1999/09/01"));
	}

	@Test
	public void testConstructor() {
		assertThrows(NullPointerException.class, () -> new Employee(null, "aa", new Date(),"a@a.it"));
		assertThrows(NullPointerException.class, () -> new Employee("ff", null, new Date(),"a@a.it"));
		assertThrows(NullPointerException.class, () -> new Employee("ff", "aa", null,"a@a.it"));
		assertThrows(NullPointerException.class, () -> new Employee("ff", "aa", new Date(),null));
	}
}
