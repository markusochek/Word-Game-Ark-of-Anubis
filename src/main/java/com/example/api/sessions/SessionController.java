package com.example.api.sessions;

import com.example.api.answer.Answer;
import com.example.api.cookies.CookieController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/sessions")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final CookieController cookieController;
    private final Answer<Session> answerSession;
    private final Answer<String> answer;

    @GetMapping
    public String getAll(HttpServletRequest request) throws JsonProcessingException {
        if(cookieController.checkToken(request) != null) {
            return answerSession.json("OK", sessionService.getAll());
        }
        return answer.json("ERROR");
    }

    @GetMapping("{id}")
    public String enterSession(@PathVariable Integer id, HttpServletRequest request) throws JsonProcessingException {
        if(cookieController.checkToken(request) != null) {
            if (sessionService.enterSession(id)) {
                return answer.json("OK");
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("checkGamerSession")
    public String checkGamerSession(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            if(sessionService.checkGamerSession(token)) {
                return answer.json("OK");
            }
        }
        return answer.json("ERROR", "Недостаточно игроков в сессии");
    }
}
