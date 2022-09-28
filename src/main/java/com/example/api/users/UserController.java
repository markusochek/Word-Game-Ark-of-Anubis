package com.example.api.users;

import com.example.api.answer.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final Answer<String> answer;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("authorization")
    public String authorization(@RequestBody User user) throws NoSuchAlgorithmException, JsonProcessingException {
        String token = userService.authorization(user);
        if (token != null) {
            return answer.json("OK", token);
        }
        return answer.json("ERROR");
    }

    @PostMapping("registration")
    public String registration(@RequestBody User user) throws NoSuchAlgorithmException, JsonProcessingException {
        return answer.json("OK", userService.registration(user));
    }
}


