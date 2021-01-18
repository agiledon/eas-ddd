package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class TrainingException extends DomainException {

    public TrainingException(String message) {
        super(message);
    }
}