package xyz.zhangyi.ddd.eas.employeecontext.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimes {
    public static boolean isValidFormat(String dateString, LocalDate minDate, LocalDate maxDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate date = LocalDate.parse(dateString, dateFormatter);
            return date.isAfter(minDate) && (date.isBefore(maxDate) || date.isEqual(maxDate));
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}