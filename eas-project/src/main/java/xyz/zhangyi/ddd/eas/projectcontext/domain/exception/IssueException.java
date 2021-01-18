package xyz.zhangyi.ddd.eas.projectcontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class IssueException extends DomainException {
    public IssueException(String message) {
        super(message);
    }
}