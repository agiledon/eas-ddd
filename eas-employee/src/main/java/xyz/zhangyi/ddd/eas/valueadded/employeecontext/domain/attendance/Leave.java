package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.attendance;

import java.time.LocalDate;

public class Leave {
    private String employeeId;
    private LocalDate askLeaveDay;
    private LeaveType leaveType;

    private Leave(String employeeId, LocalDate askLeaveDay, LeaveType leaveType) {
        this.employeeId = employeeId;
        this.askLeaveDay = askLeaveDay;
        this.leaveType = leaveType;
    }

    public static Leave of(String employeeId, LocalDate askLeaveDay, LeaveType leaveType) {
        return new Leave(employeeId, askLeaveDay, leaveType);
    }

    public AttendanceStatus attendanceStatus() {
        return leaveType.toAttendanceStatus();
    }

    public boolean sameDay(LocalDate workDay) {
        return askLeaveDay.isEqual(workDay);
    }
}