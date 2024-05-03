package cloud.englert.bigdata_datenaufbereitung.controllers;

import cloud.englert.bigdata_datenaufbereitung.datastructures.Validation;
import cloud.englert.bigdata_datenaufbereitung.validators.DateValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.GeoValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.LanguageValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.NumberValidator;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Controller for validation tasks.
 *
 * @author Linus Englert
 */
@RestController
@Tag(name = "Validation")
public class ValidationController {
    private final String PATH = "/validation";
    private static final Gson gson = new Gson();

    // Date Validator
    @GetMapping(PATH + "/date")
    ResponseEntity<Validation<Date>> validateDate(@RequestParam String value) {
        return ResponseEntity.ok(DateValidator.isDate(value));
    }
    @GetMapping(PATH + "/year")
    ResponseEntity<Validation<Integer>> validateYear(@RequestParam String value) {
        return ResponseEntity.ok(DateValidator.isYear(value));
    }

    // GeoValidator
    @GetMapping(PATH + "/long-lat")
    ResponseEntity<Validation<Double>> validateLongLat(@RequestParam String value) {
        return ResponseEntity.ok(GeoValidator.isLongLatFormat(value));
    }

    // Language Validator
    @GetMapping(value = PATH + "/language", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> validateLanguage(@RequestParam String value) {
        var validator = new LanguageValidator();
        return ResponseEntity.ok(gson.toJson(validator.getLanguage(value)));
    }

    // Number Validator
    @GetMapping(PATH + "/int")
    ResponseEntity<Validation<Integer>> validateInt(@RequestParam String value) {
        return ResponseEntity.ok(NumberValidator.isInteger(value));
    }
    @GetMapping(PATH + "/double")
    ResponseEntity<Validation<Double>> validateDouble(@RequestParam String value) {
        return ResponseEntity.ok(NumberValidator.isDouble(value));
    }
}