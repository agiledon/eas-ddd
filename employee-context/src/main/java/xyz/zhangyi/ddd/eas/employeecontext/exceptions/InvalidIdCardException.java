package xyz.zhangyi.ddd.eas.employeecontext.exceptions;

public class InvalidIdCardException extends RuntimeException {
    public InvalidIdCardException() {
        super("Invalid Id card number");
    }

    public InvalidIdCardException(String message) {
        super(message);
    }

    public InvalidIdCardException(String message, Throwable cause) {
        super(message, cause);
    }
}