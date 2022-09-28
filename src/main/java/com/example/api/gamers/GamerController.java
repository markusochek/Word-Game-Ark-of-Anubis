package com.example.api.gamers;

import com.example.api.answer.Answer;
import com.example.api.cookies.CookieController;
import com.example.api.users.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/gamers")
@AllArgsConstructor
public class GamerController {

    private final GamerService gamerService;
    private final CookieController cookieController;
    private final Answer<String> answer;
    private Answer<User> answerUser;

    @GetMapping
    public String getAll(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            List<User> users = gamerService.getAll(token);
            if(users != null) {
                return answerUser.json("OK", users);
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("accusationGamer/{WordId}")
    public String accusationGamer(@PathVariable int WordId, HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            switch (gamerService.accusationGamer(token, WordId)) {
                case "OK":
                    return answer.json("OK");
                case "ERROR":
                    return answer.json("ERROR");
                case "END":
                    return answer.json("END");
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("session/{sessionId}")
    public String gamerEnterSession(@PathVariable int sessionId,  HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            if(gamerService.gamerEnterSession(sessionId, token)) {
                return answer.json("OK");
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("hero/{heroName}")
    public String gamerHeroSelection(@PathVariable String heroName,  HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            if(gamerService.gamerHeroSelection(heroName, token)) {
                return answer.json("OK");
            }
        }
        return answer.json("ERROR");
    }
}
