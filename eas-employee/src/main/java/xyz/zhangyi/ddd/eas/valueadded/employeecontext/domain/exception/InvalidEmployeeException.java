package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class InvalidEmployeeException extends DomainException {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}