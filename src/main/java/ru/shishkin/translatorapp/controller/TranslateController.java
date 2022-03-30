package ru.shishkin.translatorapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shishkin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import ru.shishkin.translatorapp.api.yandex.request.TranslateRequestDTO;
import ru.shishkin.translatorapp.api.yandex.request.YandexAPITranslateRequestDTO;
import ru.shishkin.translatorapp.api.yandex.response.TranslateResponseDto;
import ru.shishkin.translatorapp.entity.QueryEntity;
import ru.shishkin.translatorapp.service.QueryService;
import ru.shishkin.translatorapp.service.SettingQueryService;
import ru.shishkin.translatorapp.service.YandexTranslateService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/translate")
@AllArgsConstructor
public class TranslateController {
    private final QueryService queryService;
    private final YandexTranslateService yandexTranslateService;
    private final SettingQueryService settingQueryService;

    @PostMapping
    public String translate(@RequestBody TranslateRequestDTO translateRequestDTO,
                            HttpServletRequest httpServletRequest) throws InvalidNumberLanguagesTranslateException {
        YandexAPITranslateRequestDTO yandexAPITranslateRequestDTO =
                YandexAPITranslateRequestDTO.toYandexAPITranslateRequestDTO(translateRequestDTO);
        QueryEntity queryEntity = queryService.create(httpServletRequest);
        settingQueryService.create(yandexAPITranslateRequestDTO, queryEntity);
        TranslateResponseDto translateResponseDto = yandexTranslateService.translate(yandexAPITranslateRequestDTO, queryEntity);
        return translateResponseDto.getTranslatedWords();
    }
}
