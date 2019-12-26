package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import java.time.LocalDate;

public class Attendance {
    private AttendanceStatus status;

    public Attendance(String employeeId, LocalDate workDay) {
    }

    public void assureStatus(TimeCard timeCard, Leave leave, boolean isHoliday) {
        if (timeCard.isLate()) {
            this.status = AttendanceStatus.Late;
            return;
        }
        this.status = AttendanceStatus.Normal;
    }

    public AttendanceStatus status() {
        return status;
    }
}