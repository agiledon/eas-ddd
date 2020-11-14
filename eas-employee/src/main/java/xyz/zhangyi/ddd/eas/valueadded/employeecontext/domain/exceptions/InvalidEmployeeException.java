package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class InvalidEmployeeException extends DomainException {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}