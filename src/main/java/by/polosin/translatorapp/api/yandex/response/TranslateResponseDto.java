package by.polosin.translatorapp.api.yandex.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
