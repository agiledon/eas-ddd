package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
