package ru.shishkin.translatorapp.api.yandex.exception;

public class InvalidNumberLanguagesTranslateException extends RuntimeException{
    public InvalidNumberLanguagesTranslateException(String message) {
        super(message);
    }

    public InvalidNumberLanguagesTranslateException(String message, Throwable cause) {
        super(message, cause);
    }
}
