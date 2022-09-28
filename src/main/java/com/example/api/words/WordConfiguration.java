package com.example.api.words;

import com.example.api.themes.Name;
import com.example.api.themes.Theme;
import com.example.api.themes.ThemeRepository;
import com.example.api.users.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WordConfiguration {

    private ThemeRepository themeRepository;

    @Bean
    CommandLineRunner commandLineRunnerWord(WordRepository wordRepository) {
        return args -> {
            List<Theme> themes = themeRepository.findAll();
            List<Word> words = List.of(
                    new Word(themes.get(0).getId(), "свинья"),
                    new Word(themes.get(0).getId(), "жираф"),
                    new Word(themes.get(0).getId(), "конь"),
                    new Word(themes.get(0).getId(), "обезьяна"),

                    new Word(themes.get(1).getId(), "мотоцикл"),
                    new Word(themes.get(1).getId(), "поезд"),
                    new Word(themes.get(1).getId(), "вертолет"),
                    new Word(themes.get(1).getId(), "корабль"),

                    new Word(themes.get(2).getId(), "бубен"),
                    new Word(themes.get(2).getId(), "труба"),
                    new Word(themes.get(2).getId(), "варган"),
                    new Word(themes.get(2).getId(), "гитара"),

                    new Word(themes.get(3).getId(), "копье"),
                    new Word(themes.get(3).getId(), "топор"),
                    new Word(themes.get(3).getId(), "палаш"),
                    new Word(themes.get(3).getId(), "катана"));
            for (Word word : words) {
                if (!wordRepository.existsByWord(word.getWord())) {
                    wordRepository.save(word);
                }
            }
        };
    }

}
