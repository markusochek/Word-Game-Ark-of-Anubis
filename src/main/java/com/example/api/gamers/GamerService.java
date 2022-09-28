package com.example.api.gamers;

import com.example.api.cards.Card;
import com.example.api.cards.CardRepository;
import com.example.api.heroes.Hero;
import com.example.api.heroes.HeroRepository;
import com.example.api.themes.Theme;
import com.example.api.themes.ThemeRepository;
import com.example.api.users.User;
import com.example.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GamerService {

    private final GamerRepository gamerRepository;
    private final UserRepository userRepository;
    private final HeroRepository heroRepository;
    private final ThemeRepository themeRepository;
    private final CardRepository cardRepository;

    public boolean gamerEnterSession(int sessionId, String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Gamer gamer = new Gamer();
            gamer.setUserId(entityUser.getId());
            gamer.setSessionId(sessionId);
            List<Theme> rowTheme = themeRepository.findAll();
            gamer.setThemeId(rowTheme.get(0).getId());
            gamerRepository.save(gamer);
            return true;
        }
        return false;
    }

    public boolean gamerHeroSelection(String heroName, String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Hero> rowHero = heroRepository.findByName(heroName);
            if(rowHero.isPresent()) {
                Hero entityHero = rowHero.get();
                Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
                if(rowGamer.isPresent()) {
                    Gamer entityGamer = rowGamer.get();
                    entityGamer.setHeroId(entityHero.getId());
                    gamerRepository.save(entityGamer);
                    return true;
                }
            }
        }
        return false;
    }

    public String accusationGamer(String token, int wordId) {
        Card card = cardRepository.findByWordId(wordId);
        Optional<Gamer> rowEnemy = gamerRepository.findById(card.getGamerId());
        if (rowEnemy.isPresent()) {
            Gamer entityEnemy = rowEnemy.get();
            entityEnemy.setCountOfAccusations(entityEnemy.getCountOfAccusations() + 1);
        }
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if (rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                entityGamer.setThemeId(entityGamer.getThemeId() + 1);
                gamerRepository.save(entityGamer);
                if (entityGamer.getThemeId() == 5) {
                    return "END";
                }
                return "OK";
            }
        }
        return "ERROR";
    }

    public List<User> getAll(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if (rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAllBySessionId(entityGamer.getSessionId());
                List<User> users = new ArrayList<>();
                for (Gamer gamer : gamers) {
                    Optional<User> user = userRepository.findById(gamer.getUserId());
                    if(user.isPresent()) {
                        user.get().setId(gamer.getCountOfAccusations());
                        users.add(user.get());
                    }
                }
                return users;
            }
        }
        return null;
    }
}
