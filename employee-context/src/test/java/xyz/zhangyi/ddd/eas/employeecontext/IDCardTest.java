package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidIdCardException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IDCardTest {
    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "Id card number should not be null or empty";
    public static final String DIGIT_NUMBER_ERROR_MESSAGE = "is not begin with digit number";

    @Test
    public void should_throw_InvalidIdCardException_given_null_number() {
        assertThatThrownBy(() -> new IDCard(null))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(NULL_OR_EMPTY_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_empty_number() {
        assertThatThrownBy(() -> new IDCard(""))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(NULL_OR_EMPTY_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_which_length_is_not_18() {
        assertThatThrownBy(() -> new IDCard("1234567890123456789"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("is not 18");
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_start_with_non_digital() {
        assertThatThrownBy(() -> new IDCard("X12345678901234567"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(DIGIT_NUMBER_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_start_with_0() {
        assertThatThrownBy(() -> new IDCard("012345678901234567"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(DIGIT_NUMBER_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_contains_non_digital_value_start_2_to_17() {
        assertThatThrownBy(() -> new IDCard("1X2345678901234567"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(DIGIT_NUMBER_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_end_with_other_letter() {
        assertThatThrownBy(() -> new IDCard("12345678901234567Y"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining(DIGIT_NUMBER_ERROR_MESSAGE);
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_with_invalid_birthday() {
        assertThatThrownBy(() -> new IDCard("510225198013015130"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("contains invalid birthday");
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_with_birthday_after_now() {
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strOfNow = now.format(dateFormatter);

        assertThatThrownBy(() -> new IDCard(String.format("510225%s5130", strOfNow)))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("contains wrong birthday");
    }
}