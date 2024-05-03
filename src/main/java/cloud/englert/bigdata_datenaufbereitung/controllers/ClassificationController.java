package cloud.englert.bigdata_datenaufbereitung.controllers;

import cloud.englert.bigdata_datenaufbereitung.nlp.Classifier;
import cloud.englert.bigdata_datenaufbereitung.nlp.SentimentAnalyzer;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
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
    private static final Gson gson = new Gson();

    @GetMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> classify(@RequestParam String value) {
        var classifier = new Classifier();
        return ResponseEntity.ok(gson.toJson(classifier.classify(value)));
    }

    @GetMapping(value = PATH + "/sentiment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sentiment(@RequestParam String value) {
        var classifier = new SentimentAnalyzer();
        return ResponseEntity.ok(gson.toJson(classifier.classify(value)));
    }

    @GetMapping(value = PATH + "/sentiment-wordlist", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sentimentWordlist(@RequestParam String value) {
        var classifier = new SentimentAnalyzer();
        return ResponseEntity.ok(gson.toJson(classifier.classifyByWordList(value)));
    }
}