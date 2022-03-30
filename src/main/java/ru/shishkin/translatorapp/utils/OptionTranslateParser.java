package ru.shishkin.translatorapp.utils;

import ru.shishkin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import ru.shishkin.translatorapp.api.yandex.constants.Language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionTranslateParser {
    private static final String DELIMITER_LANGUAGES_OPTIONS = "-";
    private static final String INVALID_COUNT_LANGUAGES = "Неверное количество языков для перевода";

    public static List<Language> parseTranslateLanguages(String translationLanguages) throws InvalidNumberLanguagesTranslateException {
        List<Language> languages = determineLanguages(Arrays.asList(translationLanguages.split(DELIMITER_LANGUAGES_OPTIONS)));

        if (!checkSizeLanguages(languages)) throw new InvalidNumberLanguagesTranslateException(INVALID_COUNT_LANGUAGES);

        return languages;
    }

    //To list languagesCodes
    private static List<Language> determineLanguages(List<String> languageCodes) {
        return languageCodes.stream()
                .map(Language::byCode).collect(Collectors.toList());
    }

    private static boolean checkSizeLanguages(List<Language> languages) {
        return languages.size() == 2;
    }

}
