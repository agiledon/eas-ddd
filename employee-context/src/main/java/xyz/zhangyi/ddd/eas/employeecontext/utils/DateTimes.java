package xyz.zhangyi.ddd.eas.employeecontext.utils;

import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidDateTimeFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimes {
    public static void validateFormat(String dateString, LocalDate minDate, LocalDate maxDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException ex) {
            throw new InvalidDateTimeFormatException(String.format("%s is invalid birthday", dateString), ex);
        }
        if (birthday.isBefore(minDate) || birthday.isAfter(maxDate)) {
            throw new InvalidDateTimeFormatException(String.format("%s is wrong birthday", dateString));
        }
    }
}