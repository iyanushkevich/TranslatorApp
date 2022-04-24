package by.polosin.translatorapp.controller;

import by.polosin.translatorapp.service.TranslateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import by.polosin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import by.polosin.translatorapp.api.yandex.exception.LanguageNotFoundException;
import by.polosin.translatorapp.api.yandex.request.TranslateRequestDTO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/translate")
@AllArgsConstructor
public class TranslateController {

    private final TranslateService translateService;

    @PostMapping
    public ResponseEntity<String> translate(@RequestBody TranslateRequestDTO translateRequestDTO,
                                            HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(translateService.translate(translateRequestDTO, httpServletRequest).getTranslatedWords());
        } catch (InvalidNumberLanguagesTranslateException | LanguageNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
