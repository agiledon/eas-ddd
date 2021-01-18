package xyz.zhangyi.ddd.eas.core.exception;

import xyz.zhangyi.ddd.eas.core.exception.ApplicationException;

public class ApplicationValidationException extends ApplicationException {
    public ApplicationValidationException(String message) {
        super(message);
    }
}