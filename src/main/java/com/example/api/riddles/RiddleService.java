package com.example.api.riddles;

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
public class RiddleService {
    private final UserRepository userRepository;
    private final GamerRepository gamerRepository;
    private final RiddleRepository riddleRepository;

    public boolean save(Riddle riddle, String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                riddle.setGamerId(entityGamer.getId());
                riddle.setThemeId(entityGamer.getThemeId());
                riddleRepository.save(riddle);
                return true;
            }
        }
        return false;
    }

    public String checkRiddles(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAllBySessionId(entityGamer.getSessionId());
                if (entityGamer.getThemeId() == 5) {
                    for (Gamer gamer : gamers) {
                        if (gamer.getThemeId() != 5) {
                            return "ERROR";
                        }
                    }
                    return "END";
                } else {
                    for (Gamer gamer : gamers) {
                        Optional<Riddle> rowRiddle = riddleRepository.findByGamerIdAndThemeId(gamer.getId(), entityGamer.getThemeId());
                        if (rowRiddle.isEmpty()) {
                            return "ERROR";
                        }
                    }
                    return "OK";
                }
            }
        }
        return "ERROR";
    }

    public List<Riddle> getAll(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                List<Gamer> gamers = gamerRepository.findAllBySessionId(entityGamer.getSessionId());
                List<Riddle> riddles = new ArrayList<>();
                for (Gamer gamer : gamers) {
                    Optional<Riddle> rowRiddle = riddleRepository.findByGamerIdAndThemeId(gamer.getId(), entityGamer.getThemeId());
                    if(rowRiddle.isPresent()) {
                        Riddle entityRiddle = rowRiddle.get();
                        riddles.add(entityRiddle);
                    }
                }
                return riddles;
            }
        }
        return null;
    }
}
