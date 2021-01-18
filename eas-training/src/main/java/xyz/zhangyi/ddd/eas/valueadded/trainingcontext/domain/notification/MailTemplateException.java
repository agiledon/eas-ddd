package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class MailTemplateException extends DomainException {
    public MailTemplateException(String message) {
        super(message);
    }
}