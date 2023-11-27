package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TimeConverter implements Converter<String, LocalTime> {
    private static String timePattern = "HH:mm:ss";

    public static String getTimePattern() {
        return timePattern;
    }

    public static void setTimePattern(String timePattern) {
        TimeConverter.timePattern = timePattern;
    }

    @Override
    public LocalTime convert(String source) {
        if(source.isEmpty()) return null;
        return LocalTime.parse(source, DateTimeFormatter.ofPattern(timePattern));
    }
}
