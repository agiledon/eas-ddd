package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeTest {

    private static String validName;
    private static IDCard validIdCard;
    private static Phone validPhone;
    private static Gender validGender;

    @Before
    public void setUp() {
        validName = "zhangyi";
        validIdCard = new IDCard("510222199011015130");
        validPhone = new Phone("13013220101");
        validGender = Gender.Male;
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_name_is_null() {
        String name = null;

        assertThatThrownBy(() -> new Employee(name, validIdCard, validPhone, validGender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Name should not be null or empty");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_name_is_empty() {
        String name = "";

        assertThatThrownBy(() -> new Employee(name, validIdCard, validPhone, validGender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Name should not be null or empty");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_IdCard_is_null() {
        IDCard idCard = null;

        assertThatThrownBy(() -> new Employee(validName, idCard, validPhone, validGender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("ID Card should not be null");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_mobile_phone_is_null() {
        Phone mobile = null;

        assertThatThrownBy(() -> new Employee(validName, validIdCard, mobile, validGender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Mobile Phone should not be null");
    }

    @Test
    public void should_throw_InvalidEmployeeException_if_gender_is_null() {
        Gender gender = null;

        assertThatThrownBy(() -> new Employee(validName, validIdCard, validPhone, gender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Gender should not be null");
    }
}
