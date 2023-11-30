package com.search.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.Server;
import com.search.ai.AiType;
import com.search.ai.BFS;
import com.search.ai.PokeTree;
import com.search.pokejava.Battle;
import com.search.pokejava.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class StartController {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Battle.PokeStatus>> start(@RequestBody Map<String, Object> payload) {
        Pokemon userPokemon, aiPokemon;
        ObjectMapper mapper = new ObjectMapper();
        try {
            userPokemon = mapper.convertValue(payload.get("userPokemon"), Pokemon.class);
            aiPokemon = mapper.convertValue(payload.get("aiPokemon"), Pokemon.class);
        } catch (Exception exception) {
            System.out.println("Error during conversion of pokemon objects /start.");
            logger.error(exception.toString());
            Map<String, Battle.PokeStatus> map = new HashMap<>();
            map.put("Error during conversion of pokemon objects /start.", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Map<String, Battle.PokeStatus> map = new HashMap<>();
        map.put("userStatus", new Battle.PokeStatus(userPokemon));
        map.put("aiStatus", new Battle.PokeStatus(aiPokemon));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
