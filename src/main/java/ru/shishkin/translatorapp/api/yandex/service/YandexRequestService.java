package ru.shishkin.translatorapp.api.yandex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shishkin.translatorapp.api.yandex.response.YandexApiTranslateResponseDto;

@Service
@PropertySource("classpath:resttemplate.properties")
public class YandexRequestService {
    private final RestTemplate restTemplate;
    @Value("${header.value.content.type}")
    private String HEADER_VALUE_CONTENT_TYPE;
    @Value("${header.name.content.type}")
    private String HEADER_NAME_CONTENT_TYPE;
    @Value("${header.name.authorization}")
    private String HEADER_NAME_AUTHORIZATION;
    @Value("${header.value.authorization}")
    private String HEADER_VALUE_AUTHORIZATION;

    @Autowired
    public YandexRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T, E> T postRestTemplate(E requestDto, Class<T> classResponseDto, String path) {
        HttpEntity<E> httpEntity = new HttpEntity<>(requestDto, getRequestHeadersYandexTranslate());
        return restTemplate.postForObject(path, httpEntity,
                classResponseDto);
    }

    public HttpHeaders getRequestHeadersYandexTranslate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE);
        httpHeaders.set(HEADER_NAME_AUTHORIZATION, HEADER_VALUE_AUTHORIZATION);
        return httpHeaders;
    }
}
