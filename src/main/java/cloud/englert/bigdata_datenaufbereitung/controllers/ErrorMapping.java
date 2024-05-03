package cloud.englert.bigdata_datenaufbereitung.controllers;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Error")
public class ErrorMapping implements ErrorController {
    private static final Gson gson = new Gson();

    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String error() {
        return gson.toJson("Fehler 404 - Seite nicht gefunden!");
    }
}