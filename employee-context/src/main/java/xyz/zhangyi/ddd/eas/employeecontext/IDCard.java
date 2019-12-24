package xyz.zhangyi.ddd.eas.employeecontext;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidIdCardException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDCard {
    private String number;

    public IDCard(String number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(String number) {
        validateNullOrEmptyNumber(number);
        validateLengthOfNumber(number);
        validateDigitNumbers(number);
        validateBirthdayOfNumber(number);
    }

    private void validateNullOrEmptyNumber(String number) {
        if (Strings.isNullOrEmpty(number)) {
            throw new InvalidIdCardException("Id card number should not be null or empty.");
        }
    }

    private void validateLengthOfNumber(String number) {
        if (number.length() != 18) {
            throw new InvalidIdCardException(String.format("Length of %s is not 18.", number));
        }
    }

    private void validateDigitNumbers(String number) {
        Pattern pattern = Pattern.compile("^[1-9]([0-9]{16})[xX0-9]$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            throw new InvalidIdCardException(String.format("%s is not begin with digit number.", number));
        }
    }

    private void validateBirthdayOfNumber(String number) {
        String birthdayPart = number.substring(6, 14);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate birthday = LocalDate.parse(birthdayPart, dateFormatter);
            if (birthday.isAfter(LocalDate.now())) {
                throw new InvalidIdCardException(String.format("%s contains wrong birthday", number));
            }
        } catch (DateTimeParseException ex) {
            throw new InvalidIdCardException(String.format("%s contains invalid birthday", number), ex);
        }
    }
}