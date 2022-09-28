package com.example.api.cards;

import com.example.api.words.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int gamerId;

    private int wordId;
    private int themeId;

    public Card(int gamerId, int wordId, int themeId) {
        this.gamerId = gamerId;
        this.wordId = wordId;
        this.themeId = themeId;
    }
}
