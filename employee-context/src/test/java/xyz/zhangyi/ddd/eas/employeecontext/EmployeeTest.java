package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeTest {
    @Test
    public void should_throw_InvalidEmployeeException_if_name_is_null() {
        String name = null;
        IDCard idCard = new IDCard("510222199011015130");
        Phone mobile = new Phone("13013220101");
        Gender gender = Gender.Male;

        assertThatThrownBy(() -> new Employee(name, idCard, mobile, gender))
                .isInstanceOf(InvalidEmployeeException.class)
                .hasMessageContaining("Name should not be null");
    }
}
