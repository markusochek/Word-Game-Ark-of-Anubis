package com.example.api.gamers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GamerConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerGamer(GamerRepository gamerRepository) {
        return args -> {
            gamerRepository.deleteAll();
        };
    }
}
