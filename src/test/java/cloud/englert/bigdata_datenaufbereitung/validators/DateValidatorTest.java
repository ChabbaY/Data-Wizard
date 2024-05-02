package cloud.englert.bigdata_datenaufbereitung.validators;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DateValidatorTest {
    @Test
    void shouldNotBeADate() {
        assertThat(DateValidator.isDate("1a.2a.2024").isValid()).isFalse();
    }

    @Test
    void shouldBeADate() {
        var date = Date.valueOf("2024-05-01");
        assertThat(DateValidator.isDate("5/01/2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("01.5.2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("01.05.2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("05/01/2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("5/01/2024 00:00:00").isValid()).isTrue();
        assertThat(DateValidator.isDate("01.5.2024 00:00:00").isValid()).isTrue();
        assertThat(DateValidator.isDate("01.Mai.2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("01-Mai-2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("01. 05 2024").isValid()).isTrue();
        assertThat(DateValidator.isDate("2024-05-01").isValid()).isTrue();
        assertThat(DateValidator.isDate("5/01/2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01.5.2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01.05.2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("05/01/2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("5/01/2024 00:00:00").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01.5.2024 00:00:00").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01.Mai.2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01-Mai-2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("01. 05 2024").value()).isEqualTo(date);
        assertThat(DateValidator.isDate("2024-05-01").value()).isEqualTo(date);
    }

    @Test
    void shouldBeAYear() {
        assertThat(DateValidator.isYear("2024").isValid()).isTrue();
        assertThat(DateValidator.isYear("2024").value()).isEqualTo(2024);
    }

    @Test
    void shouldNotBeAYear() {
        assertThat(DateValidator.isYear("202A").isValid()).isFalse();
    }
}