package xyz.zhangyi.ddd.eas.employeecontext.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimes {
    public static boolean isValidFormat(String dateString, LocalDate minDate, LocalDate maxDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate birthday = LocalDate.parse(dateString, dateFormatter);
            return birthday.isAfter(minDate) && birthday.isBefore(maxDate);
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}