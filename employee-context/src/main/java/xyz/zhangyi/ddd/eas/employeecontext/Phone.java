package xyz.zhangyi.ddd.eas.employeecontext;

import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidPhoneNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {
    private String number;

    public String number() {
        return this.number;
    }

    public Phone(String number) {
        validate(number);
        this.number = number;
    }

    private void validate(String number) {
        Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            throw new InvalidPhoneNumberException(String.format("%s is invalid phone number.", number));
        }
    }
}