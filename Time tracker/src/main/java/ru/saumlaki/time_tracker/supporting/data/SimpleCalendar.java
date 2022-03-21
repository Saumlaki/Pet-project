package ru.saumlaki.time_tracker.supporting.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Вспомогательный класс для работы с датой
 */
public class SimpleCalendar {

    /**
     * Возвращает начало текущего дня
     *
     * @return Calendar
     */
    public static Calendar getBeginningCurrentDay() {

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar;
    }

    /**
     * Конвертирует <Code>Date</Code> в <Code>Calendar</Code>
     *
     * @return Calendar
     */
    public static Calendar getCalendarToDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Конвертирует <Code>Calendar</Code> в строковое представление для записи в SQL
     *
     * @return String
     */
    public static String getCalendarToSQLFormat(Calendar calendar) {

        return String.format("%s-%s-%s %s:%s:%s",
                calendar.get(Calendar.YEAR),
                addSymbols(String.valueOf(calendar.get(Calendar.MONTH) + 1), "0", 2),
                addSymbols(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), "0", 2),
                addSymbols(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), "0", 2),
                addSymbols(String.valueOf(calendar.get(Calendar.MINUTE)), "0", 2),
                addSymbols(String.valueOf(calendar.get(Calendar.SECOND)), "0", 2));
    }

    /**
     * Вспомогательный класс. Добавляет нужное количество символов с начала
     *
     * @return String
     */
    private static String addSymbols(String str, String added, int length) {

        while (str.length() < length) str = added + str;
        return str;
    }
}
