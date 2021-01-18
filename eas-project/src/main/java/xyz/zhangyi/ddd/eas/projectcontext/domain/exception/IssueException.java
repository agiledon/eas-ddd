package xyz.zhangyi.ddd.eas.projectcontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class IssueException extends DomainException {
    public IssueException(String message) {
        super(message);
    }
}