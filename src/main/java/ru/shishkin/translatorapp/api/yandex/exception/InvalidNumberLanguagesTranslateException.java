package ru.shishkin.translatorapp.api.yandex.exception;

//Try catch in controller, response -> code error
public class InvalidNumberLanguagesTranslateException extends RuntimeException{
    public InvalidNumberLanguagesTranslateException(String message) {
        super(message);
    }

    public InvalidNumberLanguagesTranslateException(String message, Throwable cause) {
        super(message, cause);
    }
}
