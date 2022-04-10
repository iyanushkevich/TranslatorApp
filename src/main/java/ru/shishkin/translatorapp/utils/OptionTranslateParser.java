package ru.shishkin.translatorapp.utils;

import ru.shishkin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;

import java.util.Arrays;
import java.util.List;

public class OptionTranslateParser {
    private static final String DELIMITER_LANGUAGES_OPTIONS = "-";
    private static final String INVALID_COUNT_LANGUAGES = "Неверное количество языков для перевода";

    public static List<String> parseTranslateLanguages(String translationLanguages) throws InvalidNumberLanguagesTranslateException {
        List<String> languages = Arrays.asList(translationLanguages.split(DELIMITER_LANGUAGES_OPTIONS));

        if (!checkSizeLanguages(languages)) throw new InvalidNumberLanguagesTranslateException(INVALID_COUNT_LANGUAGES);

        return languages;
    }

    private static boolean checkSizeLanguages(List<String> languages) {
        return languages.size() == 2;
    }

}
