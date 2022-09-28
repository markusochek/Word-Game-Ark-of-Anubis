package com.example.api;

import com.example.api.answer.Answer;
import com.example.api.heroes.Hero;
import com.example.api.riddles.Riddle;
import com.example.api.sessions.Session;
import com.example.api.users.User;
import com.example.api.words.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ConfigurationApplication {

    @Bean
    Answer<String> answer() {
        return new Answer<>();
    }

    @Bean
    Answer<Session> answerSession() {
        return new Answer<>();
    }

    @Bean
    Answer<Hero> answerHero() {
        return new Answer<>();
    }

    @Bean
    Answer<Word> answerWord() {
        return new Answer<>();
    }

    @Bean
    Answer<Riddle> answerRiddle() {
        return new Answer<>();
    }

    @Bean
    Answer<User> answerUser() {
        return new Answer<>();
    }

}
