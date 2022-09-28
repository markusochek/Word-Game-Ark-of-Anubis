package com.example.api.themes;

import com.example.api.users.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class ThemeConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerTheme(ThemeRepository themeRepository) {
        return args -> {
            List<Theme> themes = List.of(
                    new Theme(Name.ANIMALS.getTitle()),
                    new Theme(Name.VEHICLES.getTitle()),
                    new Theme(Name.INSTRUMENTS.getTitle()),
                    new Theme(Name.WEAPONS.getTitle()));
            for (Theme theme : themes) {
                if (!themeRepository.existsByName(theme.getName())) {
                    themeRepository.save(theme);
                }
            }
        };
    }
}
