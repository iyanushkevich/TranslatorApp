package by.polosin.translatorapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import by.polosin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import by.polosin.translatorapp.api.yandex.request.TranslateRequestDTO;
import by.polosin.translatorapp.api.yandex.response.TranslateResponseDto;
import by.polosin.translatorapp.entity.QueryEntity;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class TranslateService {
    private final QueryService queryService;
    private final YandexTranslateService yandexTranslateService;
    private final SettingQueryService settingQueryService;

    public TranslateResponseDto translate(TranslateRequestDTO translateRequestDTO,
                                          HttpServletRequest httpServletRequest)
            throws InvalidNumberLanguagesTranslateException {
        QueryEntity queryEntity = queryService.create(httpServletRequest);
        settingQueryService.create(translateRequestDTO, queryEntity);
        return yandexTranslateService.translate(translateRequestDTO, queryEntity);
    }
}
