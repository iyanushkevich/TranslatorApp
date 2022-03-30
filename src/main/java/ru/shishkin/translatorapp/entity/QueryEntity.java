package ru.shishkin.translatorapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "query")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private String time;

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private SettingQueryEntity settingQueryEntity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TranslateEntity> translateEntities;
}
