package xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class InvalidIdCardException extends DomainException {
    public InvalidIdCardException(String message) {
        super(message);
    }
}