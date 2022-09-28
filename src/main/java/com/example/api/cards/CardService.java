package com.example.api.cards;

import com.example.api.gamers.Gamer;
import com.example.api.gamers.GamerRepository;
import com.example.api.themes.Theme;
import com.example.api.themes.ThemeRepository;
import com.example.api.users.User;
import com.example.api.users.UserRepository;
import com.example.api.words.Word;
import com.example.api.words.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {

    private UserRepository userRepository;
    private GamerRepository gamerRepository;
    private WordRepository wordRepository;
    private CardRepository cardRepository;
    private ThemeRepository themeRepository;

    public List<String> save(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Word> words = wordRepository.findAllByThemeId(entityGamer.getThemeId());
                List<Card> cards = cardRepository.findAllByThemeId(entityGamer.getThemeId());
                for (Card card : cards) {
                    words.removeIf(word -> card.getWordId() == word.getId());
                }
                Collections.shuffle(words);
                Card card = new Card(entityGamer.getId(), words.get(0).getId(), entityGamer.getThemeId());
                cardRepository.save(card);
                Optional<Theme> rowTheme = themeRepository.findById(words.get(0).getThemeId());
                    if(rowTheme.isPresent()) {
                        return  List.of(rowTheme.get().getName(), words.get(0).getWord());
                    }
            }
        }
        return null;
    }

    public List<Word> getAll(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAllBySessionId(entityGamer.getSessionId());
                List<Card> cards = new ArrayList<>();
                for (Gamer gamer : gamers) {
                    cards.add(cardRepository.findByGamerIdAndThemeId(gamer.getId(), entityGamer.getThemeId()));
                }
                List<Word> words = new ArrayList<>();
                for (Card card : cards) {
                    words.add(wordRepository.findById(card.getWordId()));
                }
                Collections.shuffle(words);
                return words;
            }
        }
        return null;
    }
}
