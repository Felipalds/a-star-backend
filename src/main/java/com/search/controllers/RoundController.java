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

import java.util.ArrayList;
import java.util.Map;

@RestController
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

        public void setLogsUser(ArrayList<String> logsUser) {
            this.logsUser = logsUser;
        }

        public void setLogsAI(ArrayList<String> logsAI) {
            this.logsAI = logsAI;
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

        public ArrayList<String> getLogsUser() {
            return logsUser;
        }

        public ArrayList<String> getLogsAI() {
            return logsAI;
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
        private ArrayList<String> logsUser, logsAI;
        private String first;

    }

    @CrossOrigin(origins = "http://10.81.70.117:5173")

    @PostMapping(value = "/round", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<RoundResponse> round(@RequestBody Map<String, Object> payload) {
        Battle.PokeStatus statusUser = (Battle.PokeStatus) payload.get("statusUser");
        Battle.PokeStatus statusAI = (Battle.PokeStatus) payload.get("statusAI");
        int userMove = (int) payload.get("userMove");
        Battle battle = new Battle(new Pokemon(statusUser.name.toLowerCase()), new Pokemon(statusAI.name.toLowerCase()));
        battle.statusA = statusUser;
        battle.statusB = statusAI;
        Battle newTurn = battle.makeTurn(userMove, 0);
        RoundResponse response = new RoundResponse();
        response.setEnded(false);
        response.setFirst(statusUser.name);
        response.setLogsAI(new ArrayList<>());
        response.setLogsUser(new ArrayList<>());
        response.setTurn(3);
        response.setStatusAI(newTurn.statusB);
        response.setStatusUser(newTurn.statusA);
        response.setAiMove(newTurn.pokemonB.moves[0].name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
