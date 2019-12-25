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

    public String number() {
        return this.number;
    }

    private void validateNumber(String number) {
        validateNullOrEmptyNumber(number);
        validateLengthOfNumber(number);
        validateDigitNumbers(number);
        validateBirthdayOfNumber(number);
        validateChecksum(number);
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
            LocalDate minDate = LocalDate.of(1900, 1, 1);
            LocalDate maxDate = LocalDate.now();
            if (birthday.isBefore(minDate) || birthday.isAfter(maxDate)) {
                throw new InvalidIdCardException(String.format("%s contains wrong birthday", number));
            }
        } catch (DateTimeParseException ex) {
            throw new InvalidIdCardException(String.format("%s contains invalid birthday", number), ex);
        }
    }

    private void validateChecksum(String number) {
        if (calculateChecksum(number) != number.charAt(17)) {
            throw new InvalidIdCardException(String.format("Checksum of %s is wrong", number));
        }
    }

    private char calculateChecksum(String number) {
        char[] checksum = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] weightOfChecksum = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < number.length() - 1; i++) {
            char current = number.charAt(i);
            sum += (current - '0') * weightOfChecksum[i];
        }
        return checksum[sum % 11];
    }
}