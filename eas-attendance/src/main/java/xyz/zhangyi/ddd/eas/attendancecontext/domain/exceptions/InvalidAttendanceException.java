package xyz.zhangyi.ddd.eas.attendancecontext.domain.exceptions;

import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;

public class InvalidAttendanceException extends DomainException {
    public InvalidAttendanceException(String message) {
        super(message);
    }
}