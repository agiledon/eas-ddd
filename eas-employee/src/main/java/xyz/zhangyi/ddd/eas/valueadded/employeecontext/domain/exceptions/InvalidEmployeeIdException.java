package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class InvalidEmployeeIdException extends DomainException {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
