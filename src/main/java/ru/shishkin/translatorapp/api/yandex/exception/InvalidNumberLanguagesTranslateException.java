package ru.shishkin.translatorapp.api.yandex.exception;

//Try catch in controller, response -> code error
public class InvalidNumberLanguagesTranslateException extends Exception{
    public InvalidNumberLanguagesTranslateException(String message) {
        super(message);
    }
}
