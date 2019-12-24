package xyz.zhangyi.ddd.eas.employeecontext.exceptions;

public class InvalidEmployeeException extends RuntimeException {
    public InvalidEmployeeException() {
        super("Invalid employee");
    }

    public InvalidEmployeeException(String message) {
        super(message);
    }
}