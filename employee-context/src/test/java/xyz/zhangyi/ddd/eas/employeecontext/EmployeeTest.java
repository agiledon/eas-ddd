package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeTest {

    private IDCard validIdCard;
    private Phone validPhone;
    private Gender validGender;
    private String validName;

    @Before
    public void setUp() throws Exception {
        validIdCard = new IDCard("510222199011015130");
        validPhone = new Phone("13013220101");
        validGender = Gender.Male;
        validName = "zhangyi";
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
}
