package com.search.controllers;

import com.search.pokejava.Battle;
import com.search.pokejava.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoundController {
    private static class RoundResponse {
        private Battle.PokeStatus statusA, statusB;


    }

    @CrossOrigin()
    @PostMapping(value = "/round", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<RoundResponse> round(@RequestBody Map<String, Object> payload) {
        RoundResponse roundResponse = new RoundResponse();
        return new ResponseEntity<>(roundResponse, HttpStatus.OK);
    }
}
