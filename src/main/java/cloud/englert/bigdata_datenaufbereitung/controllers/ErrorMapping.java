package cloud.englert.bigdata_datenaufbereitung.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Error")
public class ErrorMapping implements ErrorController {
    @GetMapping("/error")
    @ResponseBody
    public String error() {
        return "Fehler 404 - Seite nicht gefunden!";
    }
}