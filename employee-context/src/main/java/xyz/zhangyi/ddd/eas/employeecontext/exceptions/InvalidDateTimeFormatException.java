package xyz.zhangyi.ddd.eas.employeecontext.exceptions;

public class InvalidDateTimeFormatException extends RuntimeException {
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }

    public InvalidDateTimeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}