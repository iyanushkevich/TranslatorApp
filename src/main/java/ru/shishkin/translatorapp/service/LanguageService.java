package ru.shishkin.translatorapp.service;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.shishkin.translatorapp.api.yandex.request.YandexApiLanguageRequestDto;
import ru.shishkin.translatorapp.api.yandex.response.YandexApiLanguageResponseDto;
import ru.shishkin.translatorapp.api.yandex.service.YandexRequestService;
import ru.shishkin.translatorapp.entity.LanguageEntity;
import ru.shishkin.translatorapp.repository.LanguageRepo;

import java.util.List;

@Service
@NoArgsConstructor
@PropertySource("classpath:resttemplate.properties")
public class LanguageService {
    private YandexRequestService yandexRequestService;
    private LanguageRepo languageRepo;
    @Value("${yandex.language.path}")
    private String PATH;

    @Autowired
    public LanguageService(YandexRequestService yandexRequestService, LanguageRepo languageRepo) {
        this.yandexRequestService = yandexRequestService;
        this.languageRepo = languageRepo;
    }

    public String getLanguages() {
        YandexApiLanguageRequestDto yandexApiLanguageRequestDto = new YandexApiLanguageRequestDto("b1glh0rn5fgrvf019b6v");
        List<LanguageEntity> languageEntities = yandexRequestService.postRestTemplate(yandexApiLanguageRequestDto, YandexApiLanguageResponseDto.class, PATH)
                .getLanguages();
        languageRepo.saveAll(languageEntities);
        return languageEntities.toString();
    }
}
