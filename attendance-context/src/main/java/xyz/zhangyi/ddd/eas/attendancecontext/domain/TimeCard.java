package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeCard {
    private LocalDate workDay;
    private LocalTime startWork;
    private LocalTime endWork;
    private WorkTimeRule workTimeRule;

    private TimeCard(LocalDate workDay,
                     LocalTime startWork,
                     LocalTime endWork,
                     WorkTimeRule workTimeRule) {
        this.workDay = workDay;
        this.startWork = startWork;
        this.endWork = endWork;
        this.workTimeRule = workTimeRule;
    }

    public static TimeCard of(LocalDate workDay,
                              LocalTime startWork,
                              LocalTime endWork,
                              WorkTimeRule workTimeRule) {
        return new TimeCard(workDay, startWork, endWork, workTimeRule);
    }

    public boolean isLate() {
        return workTimeRule.isLate(startWork);
    }

    public boolean isLeaveEarly() {
        return workTimeRule.isLeaveEarly(endWork);
    }

    public boolean isValid() {
        return Math.abs(endWork.getHour() - startWork.getHour()) >= 4;
    }

    public boolean sameWorkDay(LocalDate workDay) {
        return this.workDay.isEqual(workDay);
    }
}