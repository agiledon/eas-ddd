package xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class NominationException extends DomainException {
    public NominationException(String message) {
        super(message);
    }
}