package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class InvalidIdCardException extends DomainException {
    public InvalidIdCardException(String message) {
        super(message);
    }
}