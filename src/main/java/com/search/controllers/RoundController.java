package com.search.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.pokejava.Battle;
import com.search.pokejava.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RoundController {
    private static class RoundResponse {

        public int getTurn() {
            return turn;
        }

        public boolean getEnded() {
            return this.ended;
        }

        public void setStatusUser(Battle.PokeStatus statusUser) {
            this.statusUser = statusUser;
        }

        public void setStatusAI(Battle.PokeStatus statusAI) {
            this.statusAI = statusAI;
        }

        public void setTurn(int turn) {
            this.turn = turn;
        }

        public void setEnded(boolean ended) {
            this.ended = ended;
        }

        public void setLogs(ArrayList<String> logs) {
            this.logs = logs;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public Battle.PokeStatus getStatusUser() {
            return statusUser;
        }

        public Battle.PokeStatus getStatusAI() {
            return statusAI;
        }

        public boolean isEnded() {
            return ended;
        }

        public ArrayList<String> getLogs() {
            return logs;
        }

        public String getFirst() {
            return first;
        }

        public String getAiMove() {
            return aiMove;
        }

        public void setAiMove(String aiMove) {
            this.aiMove = aiMove;
        }

        private Battle.PokeStatus statusUser, statusAI;
        private int turn;
        private String aiMove;
        private boolean ended;
        private ArrayList<String> logs;
        private String first;

    }



    @PostMapping(value = "/round", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<RoundResponse> round(@RequestBody Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        int userMove = (int) payload.get("userMove");
        Pokemon userPokemon = mapper.convertValue(payload.get("userPokemon"), Pokemon.class);
        Pokemon aiPokemon = mapper.convertValue(payload.get("aiPokemon"), Pokemon.class);
        System.out.println(userPokemon.moves[0]);
        System.out.println(aiPokemon.moves[0]);
        Battle.PokeStatus userStatus = mapper.convertValue(payload.get("userStatus"), Battle.PokeStatus.class);
        Battle.PokeStatus aiStatus = mapper.convertValue(payload.get("aiStatus"), Battle.PokeStatus.class);

        Battle battle = new Battle(userPokemon, aiPokemon);
        battle.statusA = userStatus;
        battle.statusB = aiStatus;

        Battle newTurn = battle.makeTurn(userMove, 0);
        RoundResponse response = new RoundResponse();
        response.setEnded(newTurn.ended);
        if (userPokemon.moves[userMove].priority > aiPokemon.moves[0].priority) {
            response.setFirst(userPokemon.name);
        } else if (userPokemon.moves[userMove].priority < aiPokemon.moves[0].priority) {
            response.setFirst(aiPokemon.name);
        } else if (userPokemon.speed >= aiPokemon.speed) {
            response.setFirst(userPokemon.name);
        } else {
            response.setFirst(aiPokemon.name);
        }
        response.setLogs(newTurn.getLogs());
        response.setTurn(newTurn.turn);
        response.setStatusAI(newTurn.statusB);
        response.setStatusUser(newTurn.statusA);
        response.setAiMove(aiPokemon.moves[0].name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
