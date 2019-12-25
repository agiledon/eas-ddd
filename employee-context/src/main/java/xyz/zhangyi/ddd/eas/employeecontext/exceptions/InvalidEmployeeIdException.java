package xyz.zhangyi.ddd.eas.employeecontext.exceptions;

public class InvalidEmployeeIdException extends RuntimeException {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
