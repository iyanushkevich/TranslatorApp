package ru.shishkin.translatorapp.api.yandex.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class YandexApiLanguageRequestDto {
    private String folderId;
}
