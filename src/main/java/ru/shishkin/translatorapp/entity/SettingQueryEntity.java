package ru.shishkin.translatorapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "setting_query")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SettingQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source")
    private String sourceLanguage;
    @Column(name = "target")
    private String targetLanguage;

    @OneToOne
    @JoinColumn(name = "query_id")
    private QueryEntity queryEntity;

}
