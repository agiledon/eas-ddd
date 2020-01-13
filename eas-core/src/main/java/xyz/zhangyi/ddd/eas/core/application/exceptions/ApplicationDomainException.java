package xyz.zhangyi.ddd.eas.core.application.exceptions;

public class ApplicationDomainException extends ApplicationException {
    public ApplicationDomainException(String message, Exception ex) {
        super(message, ex);
    }
}