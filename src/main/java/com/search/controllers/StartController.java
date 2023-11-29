package com.search.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.search.pokejava.Battle;
import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StartController {

    @CrossOrigin(origins = "http://10.81.70.117:5173")
    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Battle.PokeStatus>> start(@RequestBody Map<String, Object> payload) {
        Pokemon userPokemon, aiPokemon;
        userPokemon = new Pokemon((String) payload.get("userPokemon"));
        aiPokemon = new Pokemon((String) payload.get("aiPokemon"));
        Battle battle = new Battle(userPokemon, aiPokemon);
        Map<String, Battle.PokeStatus> map = new HashMap<>();
        map.put("userPokemon", battle.statusA);
        map.put("aiPokemon", battle.statusB);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
