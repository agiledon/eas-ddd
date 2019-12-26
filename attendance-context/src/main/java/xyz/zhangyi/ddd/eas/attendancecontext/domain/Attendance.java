package xyz.zhangyi.ddd.eas.attendancecontext.domain;

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
        if (isHoliday && invalidTimeCard(timeCard)) {
            this.status = AttendanceStatus.Holiday;
            return;
        }
        if (isHoliday && timeCard.isValid()) {
            this.status = AttendanceStatus.Overtime;
            return;
        }

        if (!isHoliday && timeCard == null && leave == null) {
            this.status = AttendanceStatus.Absence;
            return;
        }

        if (!isHoliday && timeCard == null) {
            this.status = leave.attendanceStatus();
            return;
        }

        if (timeCard.isLate() && timeCard.isLeaveEarly()) {
            this.status = AttendanceStatus.LateAndLeaveEarly;
            return;
        }
        if (timeCard.isLate()) {
            this.status = AttendanceStatus.Late;
            return;
        }
        if (timeCard.isLeaveEarly()) {
            this.status = AttendanceStatus.LeaveEarly;
            return;
        }
        this.status = AttendanceStatus.Normal;
    }

    private boolean invalidTimeCard(TimeCard timeCard) {
        return timeCard == null || !timeCard.isValid();
    }

    public AttendanceStatus status() {
        return status;
    }
}