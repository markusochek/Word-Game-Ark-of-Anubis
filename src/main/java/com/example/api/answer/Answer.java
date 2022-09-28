package com.example.api.answer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer <T> {

    private String status;
    private List<T> jsonResponse;

    public Answer(String status) {
        this.status = status;
        this.jsonResponse = null;
    }

    public Answer(String status, T jsonResponse) {
        this.status = status;
        this.jsonResponse = new ArrayList<>();
        this.jsonResponse.add(jsonResponse);

    }

    public String json(String status) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Answer<String> answer = new Answer<>(status);
        return objectMapper.writeValueAsString(answer);
    }

    public String json(String status, T response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Answer<T> answer = new Answer<>(status, response);
        return objectMapper.writeValueAsString(answer);
    }

    public String json(String status, List<T> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Answer<T> answer = new Answer<>(status, response);
        return objectMapper.writeValueAsString(answer);
    }
}
