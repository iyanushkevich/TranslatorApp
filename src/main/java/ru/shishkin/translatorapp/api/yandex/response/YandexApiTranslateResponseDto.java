package ru.shishkin.translatorapp.api.yandex.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexApiTranslateResponseDto {
    private List<YandexApiTranslateWordDto> translations;

    public List<String> getTranslatedWords() {
        return translations.stream()
                .map(YandexApiTranslateWordDto::getText).collect(Collectors.toList());
    }
}
