package cloud.englert.bigdata_datenaufbereitung.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NumberValidatorTest {
    @Test
    void shouldNotBeInteger() {
        assertThat(NumberValidator.isInteger("a").isValid()).isFalse();
    }

    @Test
    void shouldBeInteger() {
        assertThat(NumberValidator.isInteger("42").isValid()).isTrue();
        assertThat(NumberValidator.isInteger("42").value()).isEqualTo(42);
    }

    @Test
    void shouldNotBeDouble() {
        assertThat(NumberValidator.isDouble("a").isValid()).isFalse();
    }

    @Test
    void shouldBeDouble() {
        assertThat(NumberValidator.isDouble("42.24").isValid()).isTrue();
        assertThat(NumberValidator.isDouble("42.24").value()).isEqualTo(42.24d);
    }
}