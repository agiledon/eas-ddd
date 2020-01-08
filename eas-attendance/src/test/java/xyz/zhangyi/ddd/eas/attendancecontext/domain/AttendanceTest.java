package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.attendancecontext.domain.exceptions.InvalidAttendanceException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void should_be_HOLIDAY_on_holiday_without_time_card() {
        // given
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(beHoliday, null, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Holiday);
    }

    @Test
    public void should_be_HOLIDAY_on_holiday_with_invalid_time_card() {
        // given
        LocalTime punchedStartWork = LocalTime.of(9, 00);
        LocalTime punchedEndWork = LocalTime.of(12, 59);
        TimeCard timeCard = TimeCard.of(workDay, punchedStartWork, punchedEndWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(beHoliday, timeCard, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Holiday);
    }

    @Test
    public void should_be_OVERTIME_on_holiday_with_valid_time_card() {
        // given
        TimeCard timeCard = TimeCard.of(workDay, startWork, endWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(beHoliday, timeCard, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Overtime);
    }

    @Test
    public void should_be_NORMAL_on_workday_with_time_card() {
        // given
        TimeCard timeCard = TimeCard.of(workDay, startWork, endWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(notHoliday, timeCard, null);

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
        attendance.assureStatus(notHoliday, timeCard, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Late);
    }

    @Test
    public void should_be_LEAVE_EARLY_on_workday_with_time_card_and_be_earlier_than_end_work() {
        // given
        LocalTime punchedEndWork = LocalTime.of(5, 44);
        TimeCard timeCard = TimeCard.of(workDay, startWork, punchedEndWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(notHoliday, timeCard, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.LeaveEarly);
    }

    @Test
    public void should_be_LATE_AND_LEAVE_EARLY_on_workday_with_time_card_and_be_late_to_start_work_and_earlier_than_end_work() {
        // given
        LocalTime punchedStartWork = LocalTime.of(9, 16);
        LocalTime punchedEndWork = LocalTime.of(5, 44);
        TimeCard timeCard = TimeCard.of(workDay, punchedStartWork, punchedEndWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(notHoliday, timeCard, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.LateAndLeaveEarly);
    }

    @Test
    public void should_be_LEAVE_on_workday_without_time_card_and_with_leave() {
        // given
        LocalDate askLeaveDay = LocalDate.of(2019, 12, 22);
        Leave leave = Leave.of(employeeId, askLeaveDay, LeaveType.Sick);
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(notHoliday, null, leave);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.SickLeave);
    }

    @Test
    public void should_be_ABSENCE_on_workday_without_time_card_and_leave() {
        // given
        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(notHoliday, null, null);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Absence);
    }


    @Test
    public void should_throw_InvalidAttendanceException_given_time_card_with_different_workday() {
        LocalDate anotherWorkDay = LocalDate.of(2019, 12, 25);
        TimeCard timeCard = TimeCard.of(anotherWorkDay, startWork, endWork, workTimeRule);
        Attendance attendance = new Attendance(employeeId, workDay);

        assertThatThrownBy(() -> attendance.assureStatus(notHoliday, timeCard, null))
                .isInstanceOf(InvalidAttendanceException.class)
                .hasMessageContaining("different work day for attendance, time card and leave");
    }

    @Test
    public void should_throw_InvalidAttendanceException_given_leave_with_different_workday() {
        LocalDate anotherWorkDay = LocalDate.of(2019, 12, 25);
        Leave leave = Leave.of(employeeId, anotherWorkDay, LeaveType.Sick);
        Attendance attendance = new Attendance(employeeId, workDay);

        assertThatThrownBy(() -> attendance.assureStatus(notHoliday, null, leave))
                .isInstanceOf(InvalidAttendanceException.class)
                .hasMessageContaining("different work day for attendance, time card and leave");
    }
}