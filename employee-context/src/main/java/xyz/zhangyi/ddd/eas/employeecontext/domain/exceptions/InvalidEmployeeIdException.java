package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

public class InvalidEmployeeIdException extends RuntimeException {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
