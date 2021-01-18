package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class InvalidEmployeeIdException extends DomainException {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
