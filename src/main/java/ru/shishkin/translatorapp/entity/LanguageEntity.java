package ru.shishkin.translatorapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "language")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageEntity implements Serializable {
    @Id
    private String code;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageEntity")
    private List<SettingQueryEntity> settingQueryEntities;

    @Override
    public String toString() {
        return code;
    }
}
