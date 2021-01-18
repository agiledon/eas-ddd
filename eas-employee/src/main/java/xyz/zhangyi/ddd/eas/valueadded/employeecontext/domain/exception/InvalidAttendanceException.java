package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.eas.core.domain.exception.DomainException;

public class InvalidAttendanceException extends DomainException {
    public InvalidAttendanceException(String message) {
        super(message);
    }
}