package ru.shishkin.translatorapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shishkin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import ru.shishkin.translatorapp.api.yandex.request.TranslateRequestDTO;
import ru.shishkin.translatorapp.service.TranslateService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/translate")
@AllArgsConstructor
public class TranslateController {
    private TranslateService translateService;

    @PostMapping
    public ResponseEntity<String> translate(@RequestBody TranslateRequestDTO translateRequestDTO,
                                    HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(translateService.translate(translateRequestDTO, httpServletRequest).getTranslatedWords());
        } catch (InvalidNumberLanguagesTranslateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
