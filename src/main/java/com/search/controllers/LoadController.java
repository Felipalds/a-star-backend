package com.search.controllers;

import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LoadController {

    private static class LoadJson {
        private final ArrayList<LoadPokemon> pokemons = new ArrayList<>();


        public ArrayList<LoadPokemon> getPokemons() {
            return pokemons;
        }

    }

    private static class LoadPokemon {

        private String name, type;
        private int health;
        private final ArrayList<String> sprites = new ArrayList<>();
        private final ArrayList<LoadMove> moves = new ArrayList<>();


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ArrayList<String> getSprites() {
            return sprites;
        }

        public String getType() {
            return type;
        }

        public int getHealth() {
            return health;
        }

        public ArrayList<LoadMove> getMoves() {
            return moves;
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

    private static LoadPokemon PokemonToLoad(Pokemon pokemon) {
        LoadPokemon loadPokemon = new LoadPokemon();
        loadPokemon.setName(pokemon.name);
        loadPokemon.setHealth((int) pokemon.health);
        loadPokemon.setType(pokemon.type.toString());
        loadPokemon.sprites.add(pokemon.imageFront);
        loadPokemon.sprites.add(pokemon.imageBack);
        return loadPokemon;
    }

    private static LoadMove MoveToLoad(Move move) {
        LoadMove loadMove = new LoadMove();
        loadMove.setName(move.name);
        loadMove.setPower((int) move.power);
        return loadMove;
    }

    @CrossOrigin()
    @GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    private LoadJson load() {
        LoadJson startJson = new LoadJson();
        // Pikachu
        LoadPokemon pikachu = PokemonToLoad(new Pokemon("pikachu"));
        pikachu.moves.add(MoveToLoad(new Move("thunder-shock")));
        startJson.pokemons.add(pikachu);
        // Bulbasaur
        startJson.pokemons.add(PokemonToLoad(new Pokemon("bulbasaur")));
        startJson.pokemons.add(PokemonToLoad(new Pokemon("squirtle")));
        startJson.pokemons.add(PokemonToLoad(new Pokemon("charmander")));
        startJson.pokemons.add(PokemonToLoad(new Pokemon("clefairy")));
        startJson.pokemons.add(PokemonToLoad(new Pokemon("snorlax")));
        return startJson;
    }

}
