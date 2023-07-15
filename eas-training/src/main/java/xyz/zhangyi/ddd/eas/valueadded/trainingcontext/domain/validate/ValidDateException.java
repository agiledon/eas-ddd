package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validate;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class ValidDateException extends DomainException {
    public ValidDateException(String message) {
        super(message);
    }
}