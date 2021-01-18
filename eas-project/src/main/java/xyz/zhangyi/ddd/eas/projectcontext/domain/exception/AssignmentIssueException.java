package xyz.zhangyi.ddd.eas.projectcontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class AssignmentIssueException extends DomainException {
    public AssignmentIssueException(String message) {
        super(message);
    }
}