package com.example.api.cards;

import com.example.api.answer.Answer;
import com.example.api.cookies.CookieController;
import com.example.api.words.Word;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/cards")
@AllArgsConstructor
public class CardController {
    private Answer<String> answer;
    private CookieController cookieController;
    private CardService cardService;
    private Answer<Word> answerWord;

    @GetMapping
    public String getAll(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            List<Word> words = cardService.getAll(token);
            if (words != null) {
                return answerWord.json("OK", words);
            }
        }
        return answer.json("ERROR");
    }

    @GetMapping("save")
    public String save(HttpServletRequest request) throws JsonProcessingException {
        String token = cookieController.checkToken(request);
        if(token != null) {
            List<String> themeWord = cardService.save(token);
            if (themeWord != null) {
                return answer.json("OK", themeWord);
            }
        }
        return answer.json("ERROR");
    }

}
