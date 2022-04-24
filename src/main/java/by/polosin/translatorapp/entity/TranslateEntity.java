package by.polosin.translatorapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "translate_words")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TranslateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceWord;
    private String targetWord;

    @ManyToOne
    @JoinColumn(name = "query_id")
    private QueryEntity queryEntity;
}
