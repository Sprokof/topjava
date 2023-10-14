package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static int compareDates(LocalDateTime currentDate, LocalDateTime next) {
        int result = currentDate.toLocalDate().compareTo(next.toLocalDate());
        result = ((- 1) * result);
        if (0 == result) {
            result = currentDate.toLocalTime().compareTo(next.toLocalTime());
        }
        return result;
    }
}

