package com.example.api.heroes;

import com.example.api.sessions.Session;
import com.example.api.sessions.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;

@Configuration
public class HeroConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerHero(HeroRepository heroRepository) {
        return args -> {
            List<HeroesNames> newHeroes = shuffle();
            List<Hero> heroes = List.of(
                    new Hero(HeroesNames.MEDUSA.getTitle(), newHeroes.get(0).getTitle()),
                    new Hero(HeroesNames.HECATONCHIRES.getTitle(), newHeroes.get(1).getTitle()),
                    new Hero(HeroesNames.HYDRA.getTitle(), newHeroes.get(2).getTitle()),
                    new Hero(HeroesNames.PEGASUS.getTitle(), newHeroes.get(3).getTitle()));

            for (Hero hero : heroes) {
                if (!heroRepository.existsByName(hero.getName())) {
                    heroRepository.save(hero);
                }
            }
        };
    }

    public List<HeroesNames> shuffle() {
        List<HeroesNames> heroesAll = new ArrayList<>(List.of(HeroesNames.values()));
        List<HeroesNames> newHeroes = new ArrayList<>();
        int[][] selection = {
                {2, 4, 1, 3},
                {2, 3, 4, 1},
                {3, 1, 4, 2},
                {3, 4, 2, 1},
                {4, 1, 2, 3},
                {4, 3, 1, 2}};
        int i = (int) Math.floor((Math.random() * 6));
        for(int j = 0; j < heroesAll.size(); j++) {
            newHeroes.add(heroesAll.get(selection[i][j] - 1));
        }
        return newHeroes;
    }
}
