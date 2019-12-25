package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeIdException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeIdTest {
    private static String validName;
    private static IDCard validIdCard;
    private static Phone validPhone;
    private Employee employee;

    @Before
    public void setUp() {
        validName = "zhangyi";
        validIdCard = new IDCard("34052419800101001X");
        validPhone = new Phone("13013220101");
        LocalDateTime onBoardingDate = LocalDateTime.of(2019, 12, 24, 10, 0);
        employee = new Employee(validName, validIdCard, validPhone, onBoardingDate);
    }

    @Test
    public void should_generate_employee_id_given_sequence_no() {
        employee.assignIdFrom("0101");

        EmployeeId expected = new EmployeeId("201912240102");
        assertThat(employee.id()).isEqualTo(expected);
        assertThat(employee.id().sequenceCode()).isEqualTo("0102");
    }

    @Test
    public void should_throw_InvalidEmployeeIdException_given_empty_sequence_no() {
        assertThatThrownBy(() -> employee.assignIdFrom(""))
                .isInstanceOf(InvalidEmployeeIdException.class)
                .hasMessageContaining("Invalid sequence code");
    }

    @Test
    public void should_throw_InvalidEmployeeIdException_given_invalid_sequence_no() {
        assertThatThrownBy(() -> employee.assignIdFrom("xyz"))
                .isInstanceOf(InvalidEmployeeIdException.class)
                .hasMessageContaining("Invalid sequence code");
    }

    @Test
    public void should_throw_InvalidEmployeeIdException_given_sequence_no_which_greater_than_and_equal_to_9999() {
        assertThatThrownBy(() -> employee.assignIdFrom("9999"))
                .isInstanceOf(InvalidEmployeeIdException.class)
                .hasMessageContaining("Invalid max value of sequence code");
    }

    @Test
    public void should_throw_InvalidEmployeeIdException_given_null_id_value() {
        assertThatThrownBy(() -> new EmployeeId(null))
                .isInstanceOf(InvalidEmployeeIdException.class)
                .hasMessageContaining("Employee id should not be null");
    }

    @Test
    public void should_throw_InvalidEmployeeIdException_given_empty_id_value() {
        assertThatThrownBy(() -> new EmployeeId(""))
                .isInstanceOf(InvalidEmployeeIdException.class)
                .hasMessageContaining("Employee id should not be null or empty");
    }
}
