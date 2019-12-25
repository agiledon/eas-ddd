package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import java.time.LocalDate;

public class Attendance {
    private AttendanceStatus status;

    public Attendance(String employeeId, LocalDate workDay) {
    }

    public void assureStatus(TimeCard timeCard, Worktime worktime, Leave leave, boolean isHoliday) {
        this.status = AttendanceStatus.Normal;
    }

    public AttendanceStatus status() {
        return status;
    }
}