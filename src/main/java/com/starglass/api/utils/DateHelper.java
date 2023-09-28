package com.starglass.api.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {

    public static String format(Date date, String pattern) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(outputFormatter);
    }

}
