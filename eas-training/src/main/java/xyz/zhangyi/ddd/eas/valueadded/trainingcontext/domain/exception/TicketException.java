package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class TicketException extends DomainException {
    public TicketException(String message) {
        super(message);
    }
}