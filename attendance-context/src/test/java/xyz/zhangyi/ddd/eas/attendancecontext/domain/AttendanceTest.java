package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AttendanceTest {
    private LocalDate workDay;
    private String employeeId;
    private LocalTime startWork;
    private LocalTime endWork;
    private int allowableLateMinutes;
    private int allowableLeaveEarlyMinutes;
    private WorkTimeRule workTimeRule;
    private boolean beHoliday;
    private boolean notHoliday;

    @Before
    public void setUp() {
        employeeId = "201801010100";
        workDay = LocalDate.of(2019, 12, 22);
        startWork = LocalTime.of(9, 0, 0);
        endWork = LocalTime.of(18, 0, 0);
        allowableLateMinutes = 15;
        allowableLeaveEarlyMinutes = 15;
        workTimeRule = WorkTimeRule.of(startWork, endWork, allowableLateMinutes, allowableLeaveEarlyMinutes);
        beHoliday = true;
        notHoliday = false;
    }

    @Test
    public void should_be_NORMAL_on_workday_with_time_card() {
        // given
        TimeCard timeCard = TimeCard.of(workDay, startWork, endWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(timeCard, null, beHoliday);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Normal);
    }

    @Test
    public void should_be_LATE_on_workday_with_time_card_and_be_late_to_start_work() {
        // given
        LocalTime punchedStartWork = LocalTime.of(9, 16);
        TimeCard timeCard = TimeCard.of(workDay, punchedStartWork, endWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(timeCard, null, beHoliday);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Late);
    }
}