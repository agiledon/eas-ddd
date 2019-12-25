package xyz.zhangyi.ddd.eas.employeecontext;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidIdCardException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IDCardTest {
    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "Id card number should not be null or empty";
    private static final String DIGIT_NUMBER_ERROR_MESSAGE = "is not begin with digit number";
    private DateTimeFormatter dateFormatter;
    private String validIdCardNumberOfMale = "34052419800101001X";
    private String validIdCardNumberOfFemale = "510225199011015187";

    @Before
    public void setUp() {
        dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    }

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
        String strOfNow = now.format(dateFormatter);

        assertThatThrownBy(() -> new IDCard(String.format("510225%s5130", strOfNow)))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("contains wrong birthday");
    }

    @Test
    public void should_throw_InvalidIdCardException_given_number_with_birthday_before_1900() {
        assertThatThrownBy(() -> new IDCard(String.format("510225%s5130", "18991231")))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("contains wrong birthday");
    }

    @Test
    public void should_throw_InvalidIdCardException_given_wrong_checksum() {
        assertThatThrownBy(() -> new IDCard("510225199011015131"))
                .isInstanceOf(InvalidIdCardException.class)
                .hasMessageContaining("Checksum")
                .hasMessageContaining("is wrong");
    }

    @Test
    public void should_set_number_for_id_card() {
        IDCard idCard = new IDCard(validIdCardNumberOfMale);

        assertThat(idCard.number()).isEqualTo(validIdCardNumberOfMale);
    }

    @Test
    public void should_be_male_given_number_with_odd_value_at_17() {
        IDCard idCard = new IDCard(validIdCardNumberOfMale);

        assertThat(idCard.isMale()).isTrue();
    }

    @Test
    public void should_be_male_given_number_with_even_value_at_17() {
        IDCard idCard = new IDCard(validIdCardNumberOfFemale);

        assertThat(idCard.isFemale()).isTrue();
    }
}