package xyz.zhangyi.ddd.eas.employeecontext.domain;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.domain.Employee;
import xyz.zhangyi.ddd.eas.employeecontext.domain.IDCard;
import xyz.zhangyi.ddd.eas.employeecontext.domain.Phone;
import xyz.zhangyi.ddd.eas.employeecontext.domain.exceptions.InvalidEmployeeException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeTest {
    private static String validName;
    private static IDCard validIdCard;
    private static Phone validPhone;

    @Before
    public void setUp() {
        validName = "guo jing";
        validIdCard = new IDCard("34052419800101001X");
        validPhone = new Phone("13013220101");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_name_is_null() {
        String name = null;

        assertThatThrownBy(() -> new Employee(name, validIdCard, validPhone))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Name should not be null or empty");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_name_is_empty() {
        String name = "";

        assertThatThrownBy(() -> new Employee(name, validIdCard, validPhone))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Name should not be null or empty");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_IdCard_is_null() {
        IDCard idCard = null;

        assertThatThrownBy(() -> new Employee(validName, idCard, validPhone))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("ID Card should not be null");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_mobile_phone_is_null() {
        Phone mobile = null;

        assertThatThrownBy(() -> new Employee(validName, validIdCard, mobile))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Mobile Phone should not be null");
    }

    @Test
    public void should_set_correct_male_gender_given_correct_id_card() {
        Employee employee = new Employee(validName, validIdCard, validPhone);

        assertThat(employee.isMale()).isTrue();
    }

    @Test
    public void should_set_correct_female_gender_and_on_boarding_date_given_correct_id_card() {
        IDCard femaleIdCard = new IDCard("510225199011015187");
        Employee employee = new Employee(validName, femaleIdCard, validPhone);

        assertThat(employee.isFemale()).isTrue();
        assertThat(employee.onBoardingDate()).isEqualToIgnoringHours(LocalDateTime.now());
    }
}
