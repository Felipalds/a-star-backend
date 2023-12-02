package com.search.controllers;

import com.search.ai.AStar;
import com.search.ai.BFS;
import com.search.ai.PokeTree;
import com.search.pokejava.Battle;
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
        Move agility = new Move("agility");
        Move growl = new Move("growl");
        Move synthesis = new Move("synthesis");
        Move bubble = new Move("bubble");
        Move growth = new Move("growth");
        Move scratch = new Move("scratch");
        Move swordsDance = new Move("swords-dance");
        Move recover = new Move("recover");
//        Move bubbles = new Move("bubbles");
        // Pikachu
        Pokemon pikachu = new Pokemon("pikachu");
        pikachu.setMoves(tackle, thunderShock, agility, growl);
        startJson.pokemons.add(pikachu);
        // Bulbasaur
        Pokemon bulbasaur = new Pokemon("bulbasaur");
        bulbasaur.setMoves(tackle, growth, growl, synthesis);
        startJson.pokemons.add(bulbasaur);
        // Squirtle
        Pokemon squirtle = new Pokemon("squirtle");
        squirtle.setMoves(tackle, bubble, growl, harden);
        startJson.pokemons.add(squirtle);
        // Charmander
        Pokemon charmander = new Pokemon("charmander");
        charmander.setMoves(quickAttack, sharpen, scratch, growl);
        startJson.pokemons.add(charmander);
        // Clefairy
        Pokemon clefairy = new Pokemon("clefairy");
        clefairy.setMoves(tackle, megaPunch, recover, swordsDance);
        startJson.pokemons.add(clefairy);
        // Snorlax
        Pokemon snorlax = new Pokemon("snorlax");
        snorlax.setMoves(earthquake, megaPunch);
        startJson.pokemons.add(snorlax);
        return startJson;
    }

}
