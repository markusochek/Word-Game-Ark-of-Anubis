package com.example.api.cards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerCard(CardRepository cardRepository) {
        return args -> {
            cardRepository.deleteAll();
        };
    }
}
