package it.xpug.kata.birthdaygreetings.utils;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static it.xpug.kata.birthdaygreetings.utils.DateUtils.isSameDay;
import static it.xpug.kata.birthdaygreetings.utils.DateUtils.stringToDate;
import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void testStringToDate() throws GreetingsDateException {
        assertEquals    ("same date",         stringToDate("2021/01/01"), toDate(2021, 1, 1));
        assertNotEquals ("different days",    stringToDate("2021/01/01"), toDate(2021, 1, 2));
        assertThrows    ("parsing exception", GreetingsDateException.class, () -> stringToDate("blabla"));
        assertThrows    ("parsing exception", GreetingsDateException.class, () -> stringToDate(null));
    }

    private Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void testIsSameDay() throws GreetingsDateException {
        assertTrue  ("same date",             isSameDay(stringToDate("2021/01/01"), stringToDate("2021/01/01")));
        assertTrue  ("same day and month",    isSameDay(stringToDate("2021/01/01"), stringToDate("2000/01/01")));
        assertFalse ("same day and year",     isSameDay(stringToDate("2021/01/01"), stringToDate("2021/02/01")));
        assertFalse ("same month and year",   isSameDay(stringToDate("2021/01/01"), stringToDate("2021/01/02")));

        assertFalse ("with null",             isSameDay(stringToDate("2021/01/01"), null));
        assertFalse ("with null",             isSameDay(null,                   stringToDate("2021/01/01")));
    }
}
