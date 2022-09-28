package com.example.api.heroes;

import com.example.api.gamers.Gamer;
import com.example.api.gamers.GamerRepository;
import com.example.api.users.User;
import com.example.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final GamerRepository gamerRepository;
    private final UserRepository userRepository;

    public List<Hero> getAll(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if(rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAll();
                List<Hero> heroes = heroRepository.findAll();

                for(Gamer gamer : gamers) {
                    if(entityGamer.getSessionId() == gamer.getSessionId()) {
                        heroes.removeIf(hero -> hero.getId() == gamer.getHeroId());
                    }
                }
                return heroes;
            }
        }
        return null;
    }

    public List<Hero> getAllHeroes(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if(rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAllBySessionId(entityGamer.getSessionId());
                List<Hero> heroes = new ArrayList<>();
                for(Gamer gamer : gamers) {
                    Optional<Hero> rowHero = heroRepository.findById(gamer.getHeroId());
                    rowHero.ifPresent(heroes::add);
                }
                return heroes;
            }
        }
        return null;
    }

    public Hero getPrey(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if(rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if (rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                Optional<Hero> rowHero = heroRepository.findById(entityGamer.getHeroId());
                if (rowHero.isPresent()) {
                    return rowHero.get();
                }
            }
        }
        return null;
    }
}
