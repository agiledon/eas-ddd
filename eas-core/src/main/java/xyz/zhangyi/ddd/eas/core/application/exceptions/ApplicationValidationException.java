package xyz.zhangyi.ddd.eas.core.application.exceptions;

public class ApplicationValidationException extends ApplicationException {
    public ApplicationValidationException(String message) {
        super(message);
    }
}