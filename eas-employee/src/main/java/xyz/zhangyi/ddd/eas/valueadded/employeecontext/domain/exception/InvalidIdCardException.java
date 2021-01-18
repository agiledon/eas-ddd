package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class InvalidIdCardException extends DomainException {
    public InvalidIdCardException(String message) {
        super(message);
    }
}