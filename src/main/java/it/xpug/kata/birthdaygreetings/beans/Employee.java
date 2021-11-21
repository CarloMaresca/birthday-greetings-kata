package it.xpug.kata.birthdaygreetings.beans;

import it.xpug.kata.birthdaygreetings.utils.DateUtils;

import java.util.Date;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Employee {

	private Date birthDate;
	private String lastName;
	private String firstName;
	private String email;

	public Employee(String firstName, String lastName, Date birthDate, String email) {
		this.firstName = requireNonNull(firstName);
		this.lastName = requireNonNull(lastName);
		this.birthDate = requireNonNull(birthDate);
		this.email = requireNonNull(email);
	}

	public boolean isBirthday(Date today) {
		return DateUtils.isSameDay(today, birthDate);
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"birthDate=" + birthDate +
				", lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, lastName, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Employee)) return false;

		Employee other = (Employee) obj;
		return Objects.equals(birthDate, other.birthDate) &&
				Objects.equals(email, other.email) &&
				Objects.equals(firstName, other.firstName) &&
				Objects.equals(lastName, other.lastName);
	}
}
