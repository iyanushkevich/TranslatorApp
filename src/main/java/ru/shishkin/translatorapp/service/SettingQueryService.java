package ru.shishkin.translatorapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shishkin.translatorapp.api.yandex.constants.Language;
import ru.shishkin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import ru.shishkin.translatorapp.api.yandex.request.TranslateRequestDTO;
import ru.shishkin.translatorapp.entity.QueryEntity;
import ru.shishkin.translatorapp.entity.SettingQueryEntity;
import ru.shishkin.translatorapp.repository.SettingQueryRepo;
import ru.shishkin.translatorapp.utils.OptionTranslateParser;

import java.util.List;

@Service
@AllArgsConstructor
public class SettingQueryService {
    private final SettingQueryRepo settingQueryRepo;

    public SettingQueryEntity create(TranslateRequestDTO translateRequestDTO,
                                     QueryEntity queryEntity) throws InvalidNumberLanguagesTranslateException {
        SettingQueryEntity settingQueryEntity = new SettingQueryEntity();
        settingQueryEntity.setQueryEntity(queryEntity);
        setLanguagesFromRequest(translateRequestDTO, settingQueryEntity);
        settingQueryRepo.save(settingQueryEntity);
        return settingQueryEntity;
    }

    private void setLanguagesFromRequest(TranslateRequestDTO translateRequestDTO,
                                                       SettingQueryEntity settingQueryEntity)
            throws InvalidNumberLanguagesTranslateException {
        List<Language> languages = OptionTranslateParser.parseTranslateLanguages(translateRequestDTO.getTranslateLanguageOptions());
        settingQueryEntity.setSourceLanguage(languages.get(0).toString());
        settingQueryEntity.setTargetLanguage(languages.get(1).toString());
    }
}
