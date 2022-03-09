package org.motorin.serverSocket.lesson.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class LocalDateFormatter {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date){
        return LocalDate.parse(date,FORMATTER);
    }

    public boolean isValid(String date){
        try {
            format(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
