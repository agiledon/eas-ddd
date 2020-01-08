package xyz.zhangyi.ddd.eas.core.application;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class ApplicationException extends Throwable {
    public ApplicationException(String message, DomainException ex) {
        super(message, ex);
    }
}