package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class MailTemplateException extends DomainException {
    public MailTemplateException(String message) {
        super(message);
    }
}