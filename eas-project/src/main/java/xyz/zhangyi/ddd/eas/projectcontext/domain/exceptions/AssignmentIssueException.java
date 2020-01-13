package xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class AssignmentIssueException extends DomainException {
    public AssignmentIssueException(String message) {
        super(message);
    }
}