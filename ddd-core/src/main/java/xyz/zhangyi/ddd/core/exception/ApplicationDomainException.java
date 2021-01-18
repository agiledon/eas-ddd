package xyz.zhangyi.ddd.eas.core.exception;

import xyz.zhangyi.ddd.eas.core.exception.ApplicationException;

public class ApplicationDomainException extends ApplicationException {
    public ApplicationDomainException(String message, Exception ex) {
        super(message, ex);
    }
}