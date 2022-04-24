package by.polosin.translatorapp.controller;

import by.polosin.translatorapp.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
@AllArgsConstructor
public class LanguageController {
    private LanguageService languageService;
    @PostMapping
    public ResponseEntity<String> languages() {
        return ResponseEntity.ok(languageService.getLanguages());
    }
}
