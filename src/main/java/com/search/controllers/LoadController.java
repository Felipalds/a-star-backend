package com.search.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.pokejava.Move;
import com.search.pokejava.Pokemon;
import com.search.receivers.PokemonReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class LoadController {

    @Autowired
    private PokemonReceiver pokemonReceiver;
    private final ObjectMapper mapper = new ObjectMapper();

    private Move checkMoveCache(PokemonReceiver pokemonReceiver, String moveName) {
        String receivedMove = pokemonReceiver.getKey(moveName);
        Move move;
        if (receivedMove != null) {
            try {
                move = mapper.readValue(receivedMove, Move.class);
            } catch (JsonProcessingException e) {
                System.out.println("Falha carregando na cache.");
                throw new RuntimeException(e);
            }
        } else {
            move = new Move(moveName);
            try {
                pokemonReceiver.setKey(moveName, mapper.writeValueAsString(move));
            } catch (JsonProcessingException e) {
                System.out.println("Falha escrevendo na cache.");
                throw new RuntimeException(e);
            }
        }
        return move;
    }
    private Pokemon checkCache(PokemonReceiver pokemonReceiver, String pokemonName, Move ...moves) {
        String receivedPokemon = pokemonReceiver.getKey(pokemonName);
        Pokemon pokemon;
        if (receivedPokemon != null) {
            try {
                pokemon = mapper.readValue(receivedPokemon, Pokemon.class);
            } catch (JsonProcessingException e) {
                System.out.println("Falha carregando na cache.");
                throw new RuntimeException(e);
            }
        } else {
            pokemon = new Pokemon(pokemonName);
            pokemon.setMoves(moves);
            try {
                pokemonReceiver.setKey(pokemonName, mapper.writeValueAsString(pokemon));
            } catch (JsonProcessingException e) {
                System.out.println("Falha escrevendo na cache.");
                throw new RuntimeException(e);
            }
        }
        return pokemon;
    }

    private static class LoadJson {
        private final ArrayList<Pokemon> pokemons = new ArrayList<>();
        public ArrayList<Pokemon> getPokemons() {
            return pokemons;
        }

    }

    @GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    private LoadJson load() {
        LoadJson startJson = new LoadJson();
        Move tackle = checkMoveCache(pokemonReceiver, "tackle");
        Move thunderShock = checkMoveCache(pokemonReceiver, "thunder-shock");
        Move sharpen = checkMoveCache(pokemonReceiver, "sharpen");
        Move quickAttack = checkMoveCache(pokemonReceiver, "quick-attack");
        Move harden = checkMoveCache(pokemonReceiver, "harden");
        Move megaPunch = checkMoveCache(pokemonReceiver, "mega-punch");
        Move earthquake = checkMoveCache(pokemonReceiver, "earthquake");
        Move agility = checkMoveCache(pokemonReceiver, "agility");
        Move growl = checkMoveCache(pokemonReceiver, "growl");
        Move synthesis = checkMoveCache(pokemonReceiver, "synthesis");
        Move bubble = checkMoveCache(pokemonReceiver, "bubble");
        Move growth = checkMoveCache(pokemonReceiver, "growth");
        Move scratch = checkMoveCache(pokemonReceiver, "scratch");
        Move swordsDance = checkMoveCache(pokemonReceiver, "swords-dance");
        Move recover = checkMoveCache(pokemonReceiver, "recover");
        Move psychic = checkMoveCache(pokemonReceiver, "psychic");
        Move swift = checkMoveCache(pokemonReceiver, "swift");
        Move thunder = checkMoveCache(pokemonReceiver, "thunder");
        Move flamethrower = checkMoveCache(pokemonReceiver, "flamethrower");
        Move surf = checkMoveCache(pokemonReceiver, "surf");
        Move dragonBreath = checkMoveCache(pokemonReceiver, "dragon-breath");
        Move darkPulse = checkMoveCache(pokemonReceiver, "dark-pulse");
        Move calmMind = checkMoveCache(pokemonReceiver, "calm-mind");
        Move bite = checkMoveCache(pokemonReceiver, "bite");
        Move energyBall = checkMoveCache(pokemonReceiver, "energy-ball");
        Move machPunch = checkMoveCache(pokemonReceiver, "mach-punch");
        Move bulkUp = checkMoveCache(pokemonReceiver, "bulk-up");
        Move hammerArm = checkMoveCache(pokemonReceiver, "hammer-arm");
        Move ironTail = checkMoveCache(pokemonReceiver, "iron-tail");
        // Pikachu
        Pokemon pikachu = checkCache(pokemonReceiver, "pikachu", tackle, thunderShock, agility, growl);
        startJson.pokemons.add(pikachu);
        // Bulbasaur
        Pokemon bulbasaur = checkCache(pokemonReceiver, "bulbasaur", tackle, growth, growl, synthesis);
        startJson.pokemons.add(bulbasaur);
        // Squirtle
        Pokemon squirtle = checkCache(pokemonReceiver, "squirtle", tackle, bubble, growl, harden);
        startJson.pokemons.add(squirtle);
        // Charmander
        Pokemon charmander = checkCache(pokemonReceiver, "charmander", quickAttack, sharpen, scratch, growl);
        startJson.pokemons.add(charmander);
        // Clefairy
        Pokemon clefairy = checkCache(pokemonReceiver, "clefairy", tackle, megaPunch, recover, swordsDance);
        startJson.pokemons.add(clefairy);
        // Snorlax
        Pokemon snorlax = checkCache(pokemonReceiver, "snorlax", earthquake, megaPunch);
        startJson.pokemons.add(snorlax);
        // Mewtwo
        Pokemon mewtwo = checkCache(pokemonReceiver, "mewtwo", psychic, flamethrower, thunder, swift);
        startJson.pokemons.add(mewtwo);
        // Dragonite
        Pokemon dragonite = checkCache(pokemonReceiver, "dragonite", swift, dragonBreath, flamethrower, surf);
        startJson.pokemons.add(dragonite);
        // Absol
        Pokemon absol = checkCache(pokemonReceiver, "absol", darkPulse, bite, agility, calmMind);
        startJson.pokemons.add(absol);
        // Primeape
        Pokemon primeape = checkCache(pokemonReceiver, "primeape", machPunch, hammerArm, bulkUp, energyBall);
        startJson.pokemons.add(primeape);
        // Steelix
        Pokemon steelix = checkCache(pokemonReceiver, "steelix", ironTail, swordsDance, harden, swift);
        startJson.pokemons.add(steelix);
        return startJson;
    }

}
