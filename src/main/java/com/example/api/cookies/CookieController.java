package com.example.api.cookies;

import com.example.api.answer.Answer;
import com.example.api.cards.Card;
import com.example.api.cards.CardRepository;
import com.example.api.gamers.Gamer;
import com.example.api.gamers.GamerRepository;
import com.example.api.riddles.Riddle;
import com.example.api.riddles.RiddleRepository;
import com.example.api.sessions.Session;
import com.example.api.sessions.SessionRepository;
import com.example.api.sessions.Status;
import com.example.api.users.User;
import com.example.api.users.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class CookieController {
    private final Answer<String> answer;
    private CardRepository cardRepository;
    private GamerRepository gamerRepository;
    private RiddleRepository riddleRepository;
    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @PostMapping("setCookie")
    public String setCookie(
            @RequestBody CookieEntity cookieEntity,
            HttpServletResponse response) throws JsonProcessingException {
        Cookie cookie = new Cookie(cookieEntity.getName(), cookieEntity.getValue());
        if(cookie.getValue().equals("token")) {
            deleteGamerData(cookie);
        }
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return answer.json("OK");
    }

    @PostMapping
    public String checkCookie(@RequestBody CookieEntity cookieEntity, HttpServletRequest request) throws JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        String name = cookieEntity.getName();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    if(deleteGamerData(cookie)) {
                        return answer.json("OK");
                    }
                }
            }
        }
        return answer.json("ERROR");
    }

    public boolean deleteGamerData(Cookie cookie) {
        Optional<User> userRow = userRepository.findByToken(cookie.getValue());
        if(userRow.isPresent()) {
            User userEntity = userRow.get();
            Optional<Gamer> gamerRow = gamerRepository.findByUserId(userEntity.getId());
            if(gamerRow.isPresent()) {
                Gamer gamerEntity = gamerRow.get();
                List<Card> cards = cardRepository.findAllByGamerId(gamerEntity.getId());
                cardRepository.deleteAll(cards);
                List<Riddle> riddles = riddleRepository.findAllByGamerId(gamerEntity.getId());
                riddleRepository.deleteAll(riddles);
                Optional<Session> sessionRow = sessionRepository.findById(gamerEntity.getSessionId());
                if(sessionRow.isPresent()) {
                    Session sessionEntity = sessionRow.get();
                    sessionEntity.setNumberOfPlayers(sessionEntity.getNumberOfPlayers() - 1);
                    if (sessionEntity.getNumberOfPlayers() == 0) {
                        sessionEntity.setStatus(Status.EMPTY.getTitle());
                    }
                    sessionRepository.save(sessionEntity);
                    gamerRepository.deleteById(gamerEntity.getId());
                    return true;
                }
            }
        }
        return false;
    }
    public String checkToken(HttpServletRequest request) {
        String name = "token";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PostMapping("unsetCookie")
    public String unsetCookie(@RequestBody CookieEntity cookieEntity, HttpServletResponse response) throws JsonProcessingException {
        Cookie cookie = new Cookie(cookieEntity.getName(), "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return answer.json("OK");
    }
}
