package com.example.api.words;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String word;
    @Column
    private int themeId;

    public Word(int themeId, String word) {
        this.themeId = themeId;
        this.word = word;
    }

}
