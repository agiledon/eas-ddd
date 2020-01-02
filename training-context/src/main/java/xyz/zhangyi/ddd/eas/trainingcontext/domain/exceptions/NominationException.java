package xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class NominationException extends DomainException {
    public NominationException(String message) {
        super(message);
    }
}