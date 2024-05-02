package cloud.englert.bigdata_datenaufbereitung.controllers;

import cloud.englert.bigdata_datenaufbereitung.nlp.Classifier;
import cloud.englert.bigdata_datenaufbereitung.nlp.SentimentAnalyzer;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Controller for classification tasks.
 *
 * @author Linus Englert
 */
@RestController
@Tag(name = "Classification")
public class ClassificationController {
    private final String PATH = "/classification";

    @GetMapping(PATH)
    ResponseEntity<?> classify(@RequestParam String value) {
        var classifier = new Classifier();
        return ResponseEntity.ok(classifier.classify(value));
    }

    @GetMapping(PATH + "/sentiment")
    ResponseEntity<?> sentiment(@RequestParam String value) {
        var classifier = new SentimentAnalyzer();
        return ResponseEntity.ok(classifier.classify(value));
    }

    @GetMapping(PATH + "/sentiment-wordlist")
    ResponseEntity<?> sentimentWordlist(@RequestParam String value) {
        var classifier = new SentimentAnalyzer();
        return ResponseEntity.ok(classifier.classifyByWordList(value));
    }
}