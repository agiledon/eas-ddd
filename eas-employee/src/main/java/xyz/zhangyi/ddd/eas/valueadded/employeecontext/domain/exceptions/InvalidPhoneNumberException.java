package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
