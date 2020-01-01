package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class ValidDateException extends DomainException {
    public ValidDateException(String message) {
        super(message);
    }
}