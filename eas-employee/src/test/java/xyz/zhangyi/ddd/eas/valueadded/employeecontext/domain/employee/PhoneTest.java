package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee.Phone;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception.InvalidPhoneNumberException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PhoneTest {
    @Test
    public void should_throw_InvalidPhoneNumberException_given_wrong_phone_number() {
        assertThatThrownBy(() -> new Phone("11111111111"))
        .isInstanceOf(InvalidPhoneNumberException.class)
        .hasMessageContaining("invalid phone number");
    }

    @Test
    public void should_set_number_given_correct_phone_number() {
        String phoneNumber = "15023157777";
        Phone phone = new Phone(phoneNumber);

        assertThat(phone.number()).isEqualTo(phoneNumber);
    }
}