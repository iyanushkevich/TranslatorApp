package by.polosin.translatorapp.api.yandex.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexApiTranslateWordDto implements Serializable {
    private String text;
}
