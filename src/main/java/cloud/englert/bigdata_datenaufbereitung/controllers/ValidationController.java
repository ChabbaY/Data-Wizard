package cloud.englert.bigdata_datenaufbereitung.controllers;

import cloud.englert.bigdata_datenaufbereitung.validators.DateValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.GeoValidator;
import cloud.englert.bigdata_datenaufbereitung.validators.NumberValidator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller for validation tasks.
 *
 * @author Linus Englert
 */
@RestController
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