package ru.shishkin.translatorapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shishkin.translatorapp.service.LanguageService;

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
