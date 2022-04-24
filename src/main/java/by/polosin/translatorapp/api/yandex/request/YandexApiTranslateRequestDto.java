package by.polosin.translatorapp.api.yandex.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import by.polosin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import by.polosin.translatorapp.utils.OptionTranslateParser;
import by.polosin.translatorapp.utils.SplitUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class YandexApiTranslateRequestDto {
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private List<String> texts;

    public static YandexApiTranslateRequestDto toYandexAPITranslateRequestDTO(TranslateRequestDTO translateRequestDTO)
            throws InvalidNumberLanguagesTranslateException {
        List<String> languages = OptionTranslateParser.parseTranslateLanguages(translateRequestDTO.getTranslateLanguageOptions());
        List<String> sourceWords = SplitUtils.splitLineIntoWords(translateRequestDTO.getSourceText());

        return new YandexApiTranslateRequestDto(languages.get(0),
                languages.get(1),
                sourceWords);
    }

}
