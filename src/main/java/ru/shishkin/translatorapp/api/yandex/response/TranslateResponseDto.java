package ru.shishkin.translatorapp.api.yandex.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateResponseDto{
    private String translatedWords;

    public static TranslateResponseDto toTranslateResponseDto(
            YandexApiTranslateResponseDto yandexApiTranslateResponseDto) {
        return new TranslateResponseDto(String.join(" ", yandexApiTranslateResponseDto.getTranslatedWords()));
    }
}
