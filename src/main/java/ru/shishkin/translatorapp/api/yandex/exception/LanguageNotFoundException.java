package ru.shishkin.translatorapp.api.yandex.exception;

public class LanguageNotFoundException extends RuntimeException{
    public LanguageNotFoundException(String message) {
        super(message);
    }
}
