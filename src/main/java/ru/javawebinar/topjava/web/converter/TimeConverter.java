package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TimeConverter implements Converter<String, LocalTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalTime convert(String source) {
        if(source == null || source.isEmpty()) return null;
        return LocalTime.parse(source, formatter);
    }
}
