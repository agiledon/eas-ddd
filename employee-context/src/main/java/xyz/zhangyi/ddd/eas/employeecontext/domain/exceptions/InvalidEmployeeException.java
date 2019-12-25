package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

public class InvalidEmployeeException extends RuntimeException {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}