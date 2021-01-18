package xyz.zhangyi.ddd.eas.core.exception;

import xyz.zhangyi.ddd.eas.core.exception.ApplicationException;

public class ApplicationInfrastructureException extends ApplicationException {
    public ApplicationInfrastructureException(String message, Exception ex) {
        super(message, ex);
    }
}