package com.example.api.riddles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RiddleConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerRiddle(RiddleRepository riddleRepository) {
        return args -> {
            riddleRepository.deleteAll();
        };
    }
}
