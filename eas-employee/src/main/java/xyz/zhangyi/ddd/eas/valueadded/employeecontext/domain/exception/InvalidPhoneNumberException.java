package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
