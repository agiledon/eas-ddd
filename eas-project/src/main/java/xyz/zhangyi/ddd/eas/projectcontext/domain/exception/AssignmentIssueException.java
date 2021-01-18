package xyz.zhangyi.ddd.eas.projectcontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class AssignmentIssueException extends DomainException {
    public AssignmentIssueException(String message) {
        super(message);
    }
}