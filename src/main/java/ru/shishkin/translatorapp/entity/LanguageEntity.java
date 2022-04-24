package ru.shishkin.translatorapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "language")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LanguageEntity implements Serializable {
    @Id
    @Column(name = "code")
    private String code;
    private String name;

    @Override
    public String toString() {
        return code;
    }
}
