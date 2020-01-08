package xyz.zhangyi.ddd.eas.core.application;

public class ApplicationInfrastructureException extends ApplicationException {
    public ApplicationInfrastructureException(String message, Exception ex) {
        super(message, ex);
    }
}