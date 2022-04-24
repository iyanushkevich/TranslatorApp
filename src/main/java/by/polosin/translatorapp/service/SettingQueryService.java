package by.polosin.translatorapp.service;

import by.polosin.translatorapp.entity.SettingQueryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import by.polosin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import by.polosin.translatorapp.api.yandex.exception.LanguageNotFoundException;
import by.polosin.translatorapp.api.yandex.request.TranslateRequestDTO;
import by.polosin.translatorapp.entity.LanguageEntity;
import by.polosin.translatorapp.entity.QueryEntity;
import by.polosin.translatorapp.repository.LanguageRepo;
import by.polosin.translatorapp.repository.SettingQueryRepo;
import by.polosin.translatorapp.utils.OptionTranslateParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SettingQueryService {
    private static final String LANGUAGE_NOT_FOUND = "Language not found!";
    private final SettingQueryRepo settingQueryRepo;
    private final LanguageRepo languageRepo;

    public SettingQueryEntity create(TranslateRequestDTO translateRequestDTO,
                                     QueryEntity queryEntity) throws InvalidNumberLanguagesTranslateException {
        SettingQueryEntity settingQueryEntity = new SettingQueryEntity();
        settingQueryEntity.setQueryEntity(queryEntity);
        setLanguagesFromRequestToSettingQuery(settingQueryEntity, getLanguagesFromRequest(translateRequestDTO));
        settingQueryRepo.save(settingQueryEntity);
        return settingQueryEntity;
    }

    private List<LanguageEntity> getLanguagesFromRequest(TranslateRequestDTO translateRequestDTO)
            throws InvalidNumberLanguagesTranslateException {
        return OptionTranslateParser.parseTranslateLanguages(translateRequestDTO.getTranslateLanguageOptions()).stream()
                .map(item -> languageRepo.findById(item).orElseThrow(() -> new LanguageNotFoundException(LANGUAGE_NOT_FOUND)))
                .collect(Collectors.toList());
    }

    private void setLanguagesFromRequestToSettingQuery(SettingQueryEntity settingQueryEntity,
                                                       List<LanguageEntity> languageEntities) {
        settingQueryEntity.setSourceLanguage(languageEntities.get(0).getCode());
        settingQueryEntity.setTargetLanguage(languageEntities.get(1).getCode());
    }
}
