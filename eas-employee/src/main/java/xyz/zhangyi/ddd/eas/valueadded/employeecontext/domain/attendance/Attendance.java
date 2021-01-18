package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.attendance;

import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception.InvalidAttendanceException;

import java.time.LocalDate;

public class Attendance {
    private AttendanceStatus status;
    private String employeeId;
    private LocalDate workDay;

    public Attendance(String employeeId, LocalDate workDay) {
        this.employeeId = employeeId;
        this.workDay = workDay;
    }

    public void assureStatus(boolean isHoliday, TimeCard timeCard, Leave leave) {
        status = withCondition(isHoliday, timeCard, leave).toStatus();
    }

    private Condition withCondition(boolean isHoliday, TimeCard timeCard, Leave leave) {
        if (timeCard != null && !timeCard.sameWorkDay(workDay)) {
            throw new InvalidAttendanceException("different work day for attendance, time card and leave");
        }
        if (leave != null && !leave.sameDay(workDay)) {
            throw new InvalidAttendanceException("different work day for attendance, time card and leave");
        }

        return new Condition(isHoliday, timeCard, leave);
    }

    public AttendanceStatus status() {
        return status;
    }

    private static class Condition {
        private final boolean isHoliday;
        private final TimeCard timeCard;
        private final Leave leave;

        private Condition(boolean isHoliday, TimeCard timeCard, Leave leave) {
            this.isHoliday = isHoliday;
            this.timeCard = timeCard;
            this.leave = leave;
        }

        private boolean beHoliday() {
            return isHoliday && (timeCard == null || !timeCard.isValid());
        }

        private boolean beOvertime() {
            return isHoliday && timeCard.isValid();
        }

        private boolean beAbsence() {
            return !isHoliday && timeCard == null && leave == null;
        }

        private boolean beLeave() {
            return !isHoliday && timeCard == null;
        }

        private boolean beLateAndLeaveEarly() {
            return timeCard.isLate() && timeCard.isLeaveEarly();
        }

        private boolean beLate() {
            return timeCard.isLate();
        }

        private boolean beLeaveEarly() {
            return timeCard.isLeaveEarly();
        }

        private AttendanceStatus toStatus() {
            if (beHoliday()) {
                return AttendanceStatus.Holiday;
            }
            if (beOvertime()) {
                return AttendanceStatus.Overtime;
            }
            if (beAbsence()) {
                return AttendanceStatus.Absence;
            }
            if (beLeave()) {
                return leave.attendanceStatus();
            }
            if (beLateAndLeaveEarly()) {
                return AttendanceStatus.LateAndLeaveEarly;
            }
            if (beLate()) {
                return AttendanceStatus.Late;
            }
            if (beLeaveEarly()) {
                return AttendanceStatus.LeaveEarly;
            }
            return AttendanceStatus.Normal;
        }
    }
}