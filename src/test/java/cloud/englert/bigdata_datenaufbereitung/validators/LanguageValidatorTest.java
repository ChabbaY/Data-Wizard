package cloud.englert.bigdata_datenaufbereitung.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LanguageValidatorTest {
    @Test
    void recognizesGerman() {
        var validator = new LanguageValidator();
        var lang = validator.getLanguage("Wenn es regnet, ist die Straße nass.");
        assertThat(lang).isEqualTo("de");
    }

    @Test
    void recognizesEnglish() {
        var validator = new LanguageValidator();
        var lang = validator.getLanguage("Is this a good test case? Conceivably.");
        assertThat(lang).isEqualTo("en");
    }

    @Test
    void recognizesFrench() {
        var validator = new LanguageValidator();
        var lang = validator.getLanguage("Bonjour! Je cherche mon croissant.");
        assertThat(lang).isEqualTo("fr");
    }
}
