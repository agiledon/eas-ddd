package xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class IssueException extends DomainException {
    public IssueException(String message) {
        super(message);
    }
}