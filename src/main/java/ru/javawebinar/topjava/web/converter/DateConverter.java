package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter implements Converter<String, LocalDate> {
    private String datePattern = "yyyy-MM-dd";

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public LocalDate convert(String source) {
        if(source.isEmpty()) return null;
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(datePattern));
    }

}
