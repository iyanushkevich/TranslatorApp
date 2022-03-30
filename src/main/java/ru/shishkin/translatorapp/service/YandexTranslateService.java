package ru.shishkin.translatorapp.service;

//import org.apache.http.HttpEntity;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shishkin.translatorapp.api.yandex.request.YandexAPITranslateRequestDTO;
import ru.shishkin.translatorapp.api.yandex.response.TranslateResponseDto;
import ru.shishkin.translatorapp.api.yandex.response.YandexApiTranslateResponseDto;
import ru.shishkin.translatorapp.entity.QueryEntity;
import ru.shishkin.translatorapp.entity.TranslateEntity;
import ru.shishkin.translatorapp.repository.TranslateRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@PropertySource("classpath:resttemplate.properties")
public class YandexTranslateService {
    private TranslateRepo translateRepo;
    private RestTemplate restTemplate;

    @Value("${header.value.content.type}")
    private String HEADER_VALUE_CONTENT_TYPE;
    @Value("${header.name.content.type}")
    private String HEADER_NAME_CONTENT_TYPE;
    @Value("${header.name.authorization}")
    private String HEADER_NAME_AUTHORIZATION;
    @Value("${header.value.authorization}")
    private String HEADER_VALUE_AUTHORIZATION;
    @Value("${yandex.translate.path}")
    private String PATH;

    @Autowired
    public YandexTranslateService(TranslateRepo translateRepo, RestTemplate restTemplate) {
        this.translateRepo = translateRepo;
        this.restTemplate = restTemplate;
    }

    public TranslateResponseDto translate(YandexAPITranslateRequestDTO yandexAPITranslateRequestDTO,
                            QueryEntity queryEntity) {
        YandexApiTranslateResponseDto yandexApiTranslateResponseDto = translateSourceWords(yandexAPITranslateRequestDTO);
        create(yandexAPITranslateRequestDTO, yandexApiTranslateResponseDto, queryEntity);
        //Maybe return TranslateResponseDTO : collect toString()
        // yandexApiTranslateResponseDto.getTranslatedWords() and put TranslateResponseDTO
        return TranslateResponseDto.toTranslateResponseDto(yandexApiTranslateResponseDto);
    }

    private YandexApiTranslateResponseDto translateSourceWords(YandexAPITranslateRequestDTO yandexAPITranslateRequestDTO) {
        HttpEntity<YandexAPITranslateRequestDTO> httpEntity = new HttpEntity<>(yandexAPITranslateRequestDTO,
                getRequestHeadersYandexTranslate());
        YandexApiTranslateResponseDto response = restTemplate.postForObject(PATH, httpEntity,
                YandexApiTranslateResponseDto.class);
        return response;
    }

    private HttpHeaders getRequestHeadersYandexTranslate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE);
        httpHeaders.set(HEADER_NAME_AUTHORIZATION, HEADER_VALUE_AUTHORIZATION);
        return httpHeaders;
    }

    private List<TranslateEntity> create(YandexAPITranslateRequestDTO requestDTO,
                                         YandexApiTranslateResponseDto responseDto,
                                         QueryEntity queryEntity) {
        List<TranslateEntity> translateEntities = createEntities(requestDTO, responseDto, queryEntity);
        translateRepo.saveAll(translateEntities);
        return translateEntities;
    }


    private List<TranslateEntity> createEntities(YandexAPITranslateRequestDTO requestDTO,
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
        translateEntity.setQuery(queryEntity);
        return translateEntity;
    }


}
