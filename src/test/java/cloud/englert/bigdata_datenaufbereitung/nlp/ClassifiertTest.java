package cloud.englert.bigdata_datenaufbereitung.nlp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClassifiertTest {
    @Test
    void canClassify() {
        var classifier = new Classifier();
        var result = classifier.classify("Deutschland wurde 1990 Weltmeister.");
        assertThat(result).isEqualTo("Sport");
    }

    @Test
    void canSentiment() {
        var classifier = new SentimentAnalyzer();
        var result = classifier.classify("Ein traumhafter Testfall!");
        assertThat(result).isEqualTo("Gut");
    }

    @Test
    void canSentimentByWordlist() {
        var classifier = new SentimentAnalyzer();
        var result = classifier.classifyByWordList("Ein traumhafter Testfall!");
        assertThat(result).isEqualTo("Gut");
    }
}