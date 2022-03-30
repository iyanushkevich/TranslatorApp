package ru.shishkin.translatorapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shishkin.translatorapp.api.yandex.request.YandexAPITranslateRequestDTO;
import ru.shishkin.translatorapp.entity.QueryEntity;
import ru.shishkin.translatorapp.entity.SettingQueryEntity;
import ru.shishkin.translatorapp.repository.SettingQueryRepo;

@Service
@AllArgsConstructor
public class SettingQueryService {
    private final SettingQueryRepo settingQueryRepo;

    public SettingQueryEntity create(YandexAPITranslateRequestDTO yandexAPITranslateRequestDTO,
                                     QueryEntity queryEntity) {
        SettingQueryEntity settingQueryEntity = new SettingQueryEntity();

        settingQueryEntity.setQueryEntity(queryEntity);
        settingQueryEntity.setSourceLanguage(yandexAPITranslateRequestDTO.getSourceLanguageCode());
        settingQueryEntity.setTargetLanguage(yandexAPITranslateRequestDTO.getTargetLanguageCode());

        settingQueryRepo.save(settingQueryEntity);
        return settingQueryEntity;
    }
}
