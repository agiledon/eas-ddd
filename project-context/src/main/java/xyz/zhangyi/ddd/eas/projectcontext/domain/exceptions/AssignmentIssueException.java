package xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.DomainException;

public class AssignmentIssueException extends DomainException {
    public AssignmentIssueException(String message) {
        super(message);
    }
}