package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception;

import xyz.zhangyi.ddd.core.exception.DomainException;

public class InvalidAttendanceException extends DomainException {
    public InvalidAttendanceException(String message) {
        super(message);
    }
}