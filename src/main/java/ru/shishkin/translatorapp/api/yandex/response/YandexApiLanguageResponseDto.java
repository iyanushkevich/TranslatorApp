package ru.shishkin.translatorapp.api.yandex.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shishkin.translatorapp.entity.LanguageEntity;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexApiLanguageResponseDto {
    private List<LanguageEntity> languages;
}
