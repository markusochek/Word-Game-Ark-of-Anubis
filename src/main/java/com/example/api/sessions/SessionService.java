package com.example.api.sessions;

import com.example.api.gamers.Gamer;
import com.example.api.gamers.GamerRepository;
import com.example.api.users.User;
import com.example.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final GamerRepository gamerRepository;

    public boolean enterSession(Integer id) {
        Optional<Session> row = sessionRepository.findById(id);
        if (row.isPresent()) {
            Session entity = row.get();
            switch (Status.valueOf(entity.getStatus())) {
                case EMPTY:
                    entity.setStatus(Status.SELECTION.getTitle());
                    entity.setNumberOfPlayers(1);
                    ////
//                    entity.setStatus(Status.GAME.getTitle());
                    ////
                    break;

                case SELECTION:
                    if (entity.getNumberOfPlayers() != 3) {
                        entity.setNumberOfPlayers(entity.getNumberOfPlayers() + 1);
                    } else {
                        entity.setNumberOfPlayers(entity.getNumberOfPlayers() + 1);
                        entity.setStatus(Status.GAME.getTitle());
                    }
                    break;

                case GAME:
                    return false;
            }
            sessionRepository.save(entity);
            return true;
        }
        return false;
    }

    public boolean checkGamerSession(String token) {
        Optional<User> rowUser = userRepository.findByToken(token);
        if (rowUser.isPresent()) {
            User entityUser = rowUser.get();
            Optional<Gamer> rowGamer = gamerRepository.findByUserId(entityUser.getId());
            if(rowGamer.isPresent()) {
                Gamer entityGamer = rowGamer.get();
                Optional<Session> rowSession = sessionRepository.findById(entityGamer.getSessionId());
                if(rowSession.isPresent()) {
                    Session entitySession = rowSession.get();
                    return entitySession.getStatus().equals(Status.GAME.getTitle());
                }
            }
        }
        return false;
    }

    public List<Session> getAll() {
        return sessionRepository.findAll();
    }
}

