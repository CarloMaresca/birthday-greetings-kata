package it.xpug.kata.birthdaygreetings.beans;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import it.xpug.kata.birthdaygreetings.utils.DateUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BirthdayMessageTest {
    @Test
    public void testMessageAndBody() throws GreetingsDateException {
        BirthdayMessage birthdayMessage = new BirthdayMessage(
                new Employee("Paperon", "De' Paperoni", DateUtils.stringToDate("2021/01/01"), "a@b.it")
        );

        assertEquals("test from", birthdayMessage.getFrom(), "sender@here.com");
        assertEquals("test recipient", birthdayMessage.getRecipient(), "a@b.it");
        assertEquals("test body", birthdayMessage.getBody(), "Happy Birthday, dear Paperon!");
        assertEquals("test subject", birthdayMessage.getSubject(), "Happy Birthday!");
    }
}
