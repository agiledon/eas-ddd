package xyz.zhangyi.ddd.eas.attendancecontext.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AttendanceTest {
    @Test
    public void should_be_NORMAL_on_workday_with_time_card() {
        // given
        String employeeId = "201801010100";
        LocalDate workDay = LocalDate.of(2019, 12, 22);
        LocalTime startWork = LocalTime.of(9, 0, 0);
        LocalTime endWork = LocalTime.of(18, 0, 0);
        TimeCard timeCard = TimeCard.of(employeeId, workDay, startWork, endWork);
        Worktime worktime = Worktime.of(startWork, endWork);
        boolean isHoliday = false;

        Attendance attendance = new Attendance(employeeId, workDay);

        // when
        attendance.assureStatus(timeCard, worktime, null, isHoliday);

        // then
        assertThat(attendance.status()).isEqualTo(AttendanceStatus.Normal);
    }
}