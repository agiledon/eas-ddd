package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException() {
        super("Invalid phone number.");
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
