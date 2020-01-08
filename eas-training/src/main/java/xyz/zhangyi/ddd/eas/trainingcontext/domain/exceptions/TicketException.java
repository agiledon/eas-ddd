package xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class TicketException extends DomainException {
    public TicketException(String message) {
        super(message);
    }
}