package by.polosin.translatorapp.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.polosin.translatorapp.api.yandex.exception.InvalidNumberLanguagesTranslateException;
import by.polosin.translatorapp.api.yandex.request.TranslateRequestDTO;
import by.polosin.translatorapp.api.yandex.request.YandexApiTranslateRequestDto;
import by.polosin.translatorapp.api.yandex.response.TranslateResponseDto;
import by.polosin.translatorapp.api.yandex.response.YandexApiTranslateResponseDto;
import by.polosin.translatorapp.api.yandex.service.YandexRequestService;
import by.polosin.translatorapp.entity.QueryEntity;
import by.polosin.translatorapp.entity.TranslateEntity;
import by.polosin.translatorapp.repository.TranslateRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class YandexTranslateService {
    private YandexRequestService yandexRequestService;
    private TranslateRepo translateRepo;

    private String PATH = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    @Autowired
    public YandexTranslateService(TranslateRepo translateRepo, YandexRequestService yandexRequestService) {
        this.translateRepo = translateRepo;
        this.yandexRequestService = yandexRequestService;
    }

    public TranslateResponseDto translate(TranslateRequestDTO translateRequestDTO,
                                          QueryEntity queryEntity) throws InvalidNumberLanguagesTranslateException {
        YandexApiTranslateRequestDto yandexAPITranslateRequestDTO =
                YandexApiTranslateRequestDto.toYandexAPITranslateRequestDTO(translateRequestDTO);
        YandexApiTranslateResponseDto yandexApiTranslateResponseDto = translateSourceWords(yandexAPITranslateRequestDTO);

        create(yandexAPITranslateRequestDTO, yandexApiTranslateResponseDto, queryEntity);
        return TranslateResponseDto.toTranslateResponseDto(yandexApiTranslateResponseDto);
    }

    private YandexApiTranslateResponseDto translateSourceWords(YandexApiTranslateRequestDto yandexAPITranslateRequestDTO) {
        return yandexRequestService.postRestTemplate(yandexAPITranslateRequestDTO, YandexApiTranslateResponseDto.class, PATH);
    }

    private List<TranslateEntity> create(YandexApiTranslateRequestDto requestDTO,
                                         YandexApiTranslateResponseDto responseDto,
                                         QueryEntity queryEntity) {
        List<TranslateEntity> translateEntities = createEntities(requestDTO, responseDto, queryEntity);
        translateRepo.saveAll(translateEntities);
        return translateEntities;
    }


    private List<TranslateEntity> createEntities(YandexApiTranslateRequestDto requestDTO,
                                                 YandexApiTranslateResponseDto responseDto,
                                                 QueryEntity queryEntity) {
        List<String> sourceWords = requestDTO.getTexts();
        List<String> translatedWords = responseDto.getTranslatedWords();

        return createEntities(sourceWords, translatedWords, queryEntity);
    }

    private List<TranslateEntity> createEntities(List<String> sourceWords,
                                                 List<String> translatedWords,
                                                 QueryEntity queryEntity) {
        List<TranslateEntity> translateEntities = new ArrayList<>();

        for (int i = 0; i < sourceWords.size(); i++) {
            translateEntities.add(createEntity(sourceWords.get(i), translatedWords.get(i), queryEntity));
        }

        return translateEntities;
    }

    private TranslateEntity createEntity(String sourceWord,
                                         String targetWord,
                                         QueryEntity queryEntity) {
        TranslateEntity translateEntity = new TranslateEntity();
        translateEntity.setSourceWord(sourceWord);
        translateEntity.setTargetWord(targetWord);
        translateEntity.setQueryEntity(queryEntity);
        return translateEntity;
    }


}