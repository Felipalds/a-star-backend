package com.search.controllers;

import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class LoadController {

    private static class LoadJson {
        private final ArrayList<Pokemon> pokemons = new ArrayList<>();
        public ArrayList<Pokemon> getPokemons() {
            return pokemons;
        }

    }

    @GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    private LoadJson load() {
        LoadJson startJson = new LoadJson();
        Move tackle = new Move("tackle");
        Move thunderShock = new Move("thunder-shock");
        Move sharpen = new Move("sharpen");
        Move quickAttack = new Move("quick-attack");
        Move harden = new Move("harden");
        Move megaPunch = new Move("mega-punch");
        Move earthquake = new Move("earthquake");
//        Move bubbles = new Move("bubbles");
        // Pikachu
        Pokemon pikachu = new Pokemon("pikachu");
        pikachu.setMoves(tackle, thunderShock);
        startJson.pokemons.add(pikachu);
        // Bulbasaur
        Pokemon bulbasaur = new Pokemon("bulbasaur");
        bulbasaur.setMoves(tackle, harden);
        startJson.pokemons.add(bulbasaur);
        // Squirtle
        Pokemon squirtle = new Pokemon("squirtle");
        squirtle.setMoves(tackle);
        startJson.pokemons.add(squirtle);
        // Charmander
        Pokemon charmander = new Pokemon("charmander");
        charmander.setMoves(quickAttack, sharpen);
        startJson.pokemons.add(charmander);
        // Clefairy
        Pokemon clefairy = new Pokemon("clefairy");
        clefairy.setMoves(tackle, megaPunch);
        startJson.pokemons.add(clefairy);
        // Snorlax
        Pokemon snorlax = new Pokemon("snorlax");
        snorlax.setMoves(earthquake, megaPunch);
        startJson.pokemons.add(snorlax);
        return startJson;
    }

}
