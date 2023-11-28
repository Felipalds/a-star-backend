package com.search.controllers;

import com.search.pokejava.Battle;
import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import com.search.pokejava.types.DamageType;
import com.search.pokejava.types.PokeType;
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
        private String name, description;
        private PokeType pokeType;
        private DamageType damageType;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public PokeType getPokeType() {
            return pokeType;
        }

        public void setPokeType(PokeType pokeType) {
            this.pokeType = pokeType;
        }

        public DamageType getDamageType() {
            return damageType;
        }

        public void setDamageType(DamageType damageType) {
            this.damageType = damageType;
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
        loadMove.setDescription(move.description);
        loadMove.setPokeType(move.pokeType);
        loadMove.setDamageType(move.damageType);
        return loadMove;
    }

    @CrossOrigin()
    @GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    private LoadJson load() {
        LoadJson startJson = new LoadJson();
        LoadMove tackle = MoveToLoad(new Move("tackle"));
        LoadMove thunderShock = MoveToLoad(new Move("thunder-shock"));
        LoadMove ember = MoveToLoad(new Move("ember"));
        LoadMove earthquake = MoveToLoad(new Move("earthquake"));
        LoadMove sharpen = MoveToLoad(new Move("sharpen"));
        // Pikachu
        LoadPokemon pikachu = PokemonToLoad(new Pokemon("pikachu"));
        pikachu.moves.add(thunderShock);
        pikachu.moves.add(tackle);
        startJson.pokemons.add(pikachu);
        // Bulbasaur
        LoadPokemon bulbasaur = PokemonToLoad(new Pokemon("bulbasaur"));
        bulbasaur.moves.add(tackle);
//        bulbasaur.moves.add(MoveToLoad(new Move("quick-attack")));
        startJson.pokemons.add(bulbasaur);
        // Squirtle
        LoadPokemon squirtle = PokemonToLoad(new Pokemon("squirtle"));
        squirtle.moves.add(tackle);
        startJson.pokemons.add(squirtle);
        // Charmander
        LoadPokemon charmander = PokemonToLoad(new Pokemon("charmander"));
        charmander.moves.add(tackle);
        charmander.moves.add(ember);
        charmander.moves.add(sharpen);
        startJson.pokemons.add(charmander);
        // Clefairy
        LoadPokemon clefairy = PokemonToLoad(new Pokemon("clefairy"));
        clefairy.moves.add(tackle);
        startJson.pokemons.add(clefairy);
        // Snorlax
        LoadPokemon snorlax = PokemonToLoad(new Pokemon("snorlax"));
        startJson.pokemons.add(snorlax);
        snorlax.moves.add(earthquake);
        return startJson;
    }

}
