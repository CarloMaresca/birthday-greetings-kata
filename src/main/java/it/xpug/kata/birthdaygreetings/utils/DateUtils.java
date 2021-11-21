package it.xpug.kata.birthdaygreetings.utils;

import it.xpug.kata.birthdaygreetings.exceptions.GreetingsDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DateUtils {
    public static final String YYYY_MM_DD = "yyyy/MM/dd";

    public static Date stringToDate(String date) throws GreetingsDateException {
        if(Objects.isNull(date)) throw new GreetingsDateException("invalid date: null");
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            throw new GreetingsDateException(e.getMessage(), e);
        }
    }

    public static boolean isSameDay(Date date, Date otherDate) {
        return  Objects.nonNull(date) &&
                Objects.nonNull(otherDate) &&
                getDay(date) == getDay(otherDate) &&
                getMonth(date) == getMonth(otherDate);
    }

    private static int getDay(Date date) {
        return getPartOfDate(date, GregorianCalendar.DAY_OF_MONTH);
    }

    private static int getPartOfDate(Date date, int part) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(part);
    }

    private static int getMonth(Date date) {
        return getPartOfDate(date, GregorianCalendar.MONTH) + 1;
    }
}
