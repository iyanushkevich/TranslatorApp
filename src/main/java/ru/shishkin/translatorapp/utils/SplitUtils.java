package ru.shishkin.translatorapp.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SplitUtils {
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("(?U)\\W+");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");

    private SplitUtils() {};

    public static List<String> splitLineIntoWords(String str) {
        return Arrays.stream(str.trim().split(DELIMITER_PATTERN.pattern()))
                .filter(item -> !item.matches(NUMBER_PATTERN.pattern()))
                .map(String::toLowerCase).collect(Collectors.toList());
    }
}
