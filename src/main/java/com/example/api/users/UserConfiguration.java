package com.example.api.users;

import com.example.api.themes.Theme;
import com.example.api.themes.ThemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            List<User> users = List.of(
                new User("gamer1", "gamer1", "A06340AAF65D93A96C5F1EF1A0BBD885", "00060426F7A9D5175CBF8124ED7CA73E"),
                new User("gamer2", "gamer2", "2686DD3EB541554F2102DBB3D7D5B5D0", "6E1CB6C4ABF60540631A0AC471163967"),
                new User("gamer3", "gamer3", "C678A288E0869E2260463C87CCC3A31A", "FC293B65453AE16A6F512DA810981C6B"),
                new User("gamer4", "gamer4", "A1D0640AD9EE292908B5B138786FDD74", "769401BBC214F0A20922DACBEC474F0E"));
            for (User user : users) {
                if (!userRepository.existsByLogin(user.getLogin())) {
                    userRepository.save(user);
                }
            }
        };
    }
}
