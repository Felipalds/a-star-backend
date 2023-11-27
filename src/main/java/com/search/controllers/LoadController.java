package com.search.controllers;

import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class LoadController {

    private static class LoadJson {
        private ArrayList<LoadPokemon> loadPokemons = new ArrayList<>();
        private ArrayList<LoadMove> loadMoves = new ArrayList<>();

        public void setLoadPokemons(ArrayList<LoadPokemon> loadPokemons) {
            this.loadPokemons = loadPokemons;
        }

        public void setLoadMoves(ArrayList<LoadMove> loadMoves) {
            this.loadMoves = loadMoves;
        }

        public ArrayList<LoadPokemon> getLoadPokemons() {
            return loadPokemons;
        }

        public ArrayList<LoadMove> getLoadMoves() {
            return loadMoves;
        }
    }

    private static class LoadPokemon {

        private String name, type;
        private int health;
        private ArrayList<String> imagesList = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getImagesList() {
            return imagesList;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setImagesList(ArrayList<String> imagesList) {
            this.imagesList = imagesList;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public int getHealth() {
            return health;
        }
    }

    private static class LoadMove {
        private String name;
        private int power;

        public void setName(String name) {
            this.name = name;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public int getPower() {
            return power;
        }
    }



    @GetMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    private LoadJson load() {
        LoadJson startJson = new LoadJson();
        LoadPokemon loadPikachu = new LoadPokemon();
        Pokemon pikachu = new Pokemon("pikachu");
        loadPikachu.setHealth((int) pikachu.health);
        loadPikachu.setName(pikachu.name);
        loadPikachu.setType(pikachu.type.toString());
        loadPikachu.imagesList.add(pikachu.imageFront);
        loadPikachu.imagesList.add(pikachu.imageBack);
        startJson.loadPokemons.add(loadPikachu);
        Move thunderShock = new Move("thunder-shock");
        LoadMove loadThunderShock = new LoadMove();
        loadThunderShock.setName(thunderShock.name);
        loadThunderShock.setPower((int) thunderShock.power);
        startJson.loadMoves.add(loadThunderShock);

        return startJson;
    }

}
