package ru.shishkin.translatorapp.api.yandex.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TranslateRequestDTO {
    @NonNull
    private String translateLanguageOptions;
    private String sourceText;
}
