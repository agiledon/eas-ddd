package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import java.time.LocalTime;

public class WorkTimeRule {
    private LocalTime startWork;
    private LocalTime endWork;
    private int allowableLateMinutes;
    private int allowableLeaveEarlyMinutes;

    private WorkTimeRule(LocalTime startWork, LocalTime endWork, int allowableLateMinutes, int allowableLeaveEarlyMinutes) {
        this.startWork = startWork;
        this.endWork = endWork;
        this.allowableLateMinutes = allowableLateMinutes;
        this.allowableLeaveEarlyMinutes = allowableLeaveEarlyMinutes;
    }

    public static WorkTimeRule of(LocalTime startWork, LocalTime endWork, int allowableLateMinutes, int allowableLeaveEarlyMinutes) {
        return new WorkTimeRule(startWork, endWork, allowableLateMinutes, allowableLeaveEarlyMinutes);
    }

    public boolean isLate(LocalTime punchedTime) {
        return punchedTime.isAfter(startWork.plusMinutes(allowableLateMinutes));
    }
}