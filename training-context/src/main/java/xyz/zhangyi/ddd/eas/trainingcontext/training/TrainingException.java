package xyz.zhangyi.ddd.eas.trainingcontext.training;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class TrainingException extends DomainException {

    public TrainingException(String message) {
        super(message);
    }
}