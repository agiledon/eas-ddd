package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

public class InvalidIdCardException extends RuntimeException {
    public InvalidIdCardException(String message) {
        super(message);
    }
}