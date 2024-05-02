package cloud.englert.bigdata_datenaufbereitung.controllers;

import cloud.englert.bigdata_datenaufbereitung.validators.DateValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.GeoValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.LanguageValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.NumberValidator;

import io.swagger.v3.oas.annotations.tags.Tag;

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

    // Date Validator
    @GetMapping(PATH + "/date")
    ResponseEntity<?> validateDate(@RequestParam String value) {
        return ResponseEntity.ok(DateValidator.isDate(value));
    }
    @GetMapping(PATH + "/year")
    ResponseEntity<?> validateYear(@RequestParam String value) {
        return ResponseEntity.ok(DateValidator.isYear(value));
    }

    // GeoValidator
    @GetMapping(PATH + "/long-lat")
    ResponseEntity<?> validateLongLat(@RequestParam String value) {
        return ResponseEntity.ok(GeoValidator.isLongLatFormat(value));
    }

    // Language Validator
    @GetMapping(PATH + "/language")
    ResponseEntity<?> validateLanguage(@RequestParam String value) {
        var validator = new LanguageValidator();
        return ResponseEntity.ok(validator.getLanguage(value));
    }

    // Number Validator
    @GetMapping(PATH + "/int")
    ResponseEntity<?> validateInt(@RequestParam String value) {
        return ResponseEntity.ok(NumberValidator.isInteger(value));
    }
    @GetMapping(PATH + "/double")
    ResponseEntity<?> validateDouble(@RequestParam String value) {
        return ResponseEntity.ok(NumberValidator.isDouble(value));
    }
}