package ru.shishkin.translatorapp.api.yandex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YandexRequestService {

    private final RestTemplate restTemplate;
    private String HEADER_VALUE_CONTENT_TYPE = "application/json; utf-8";
    private String HEADER_NAME_CONTENT_TYPE = "Content-Type";
    private String HEADER_NAME_AUTHORIZATION = "Authorization";
    private String HEADER_VALUE_AUTHORIZATION = "Api-key AQVNyjDewia7jOvC78FqZ_gIlpmZIms2siBnRQBP";


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
