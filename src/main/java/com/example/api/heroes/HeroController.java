package com.example.api.heroes;

import com.example.api.answer.Answer;
import com.example.api.cookies.CookieController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/heroes")
@AllArgsConstructor
public class HeroController {

    private final HeroService heroService;
    private final CookieController cookieController;
    private final Answer<Hero> answerHero;
    private final Answer<String> answer;

    @GetMapping("getPrey")
    public String getPrey(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            return answerHero.json("OK", heroService.getPrey(token));
        }
        return answer.json("ERROR");
    }

    @GetMapping("all")
    public String getAllHeroes(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            return answerHero.json("OK", heroService.getAllHeroes(token));
        }
        return answer.json("ERROR");
    }

    @GetMapping
    public String getAll(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            return answerHero.json("OK", heroService.getAll(token));
        }
        return answer.json("ERROR");
    }
}
