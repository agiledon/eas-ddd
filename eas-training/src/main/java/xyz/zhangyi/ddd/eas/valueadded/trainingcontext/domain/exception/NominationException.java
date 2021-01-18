package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class NominationException extends DomainException {
    public NominationException(String message) {
        super(message);
    }
}