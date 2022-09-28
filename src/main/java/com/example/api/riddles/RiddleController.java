package com.example.api.riddles;

import com.example.api.answer.Answer;
import com.example.api.cookies.CookieController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/riddles")
@AllArgsConstructor
public class RiddleController {

    private final CookieController cookieController;
    private final Answer<String> answer;
    private final RiddleService riddleService;
    private Answer<Riddle> answerRiddle;

    @GetMapping
    public String getAll(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            List<Riddle> riddles = riddleService.getAll(token);
            if (riddles != null) {
                return answerRiddle.json("OK", riddles);
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("checkRiddles")
    public String checkRiddles(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            switch (riddleService.checkRiddles(token)) {
                case "OK":
                    return answer.json("OK");
                case "ERROR":
                    return answer.json("ERROR", "ещё не все ответили");
                case "END":
                    return answer.json("END");
            }
        }
        return answer.json("ERROR", "ещё не все ответили");
    }

    @PostMapping("save")
    public String save(@RequestBody Riddle riddle, HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            if (riddleService.save(riddle, token)) {
                return answer.json("OK");
            }
        }
        return answer.json("ERROR");
    }

}
