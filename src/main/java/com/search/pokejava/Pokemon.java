package com.search.pokejava;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.pokejava.types.PokeType;
import org.apache.tomcat.util.json.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Pokemon {

    public final Move[] moves = new Move[4];
    public String name, imageFront, imageBack;
    public float health, attack, specialAttack, defense, specialDefense, speed;
    public PokeType type, secondType;

    public Pokemon(Pokemon pokemon) {
        if (pokemon.moves[0] != null) {
            this.moves[0] = new Move(pokemon.moves[0]);
        }
        if (pokemon.moves[1] != null) {
            this.moves[1] = new Move(pokemon.moves[1]);
        }
        if (pokemon.moves[2] != null) {
            this.moves[2] = new Move(pokemon.moves[2]);
        }
        if (pokemon.moves[3] != null) {
            this.moves[3] = new Move(pokemon.moves[3]);
        }
        this.health = pokemon.health;
        this.specialAttack = pokemon.specialAttack;
        this.specialDefense = pokemon.specialDefense;
        this.speed = pokemon.speed;
        this.defense = pokemon.defense;
        this.attack = pokemon.attack;
        this.type = pokemon.type;
        this.name = pokemon.name;
        this.imageFront = pokemon.imageFront;
        this.imageBack = pokemon.imageBack;
        this.secondType = pokemon.secondType;
    }

    public Pokemon(String name) {
        String urlString = "https://pokeapi.co/api/v2/pokemon/" + name;
        URL url = null;
        try {
            url = new URI(urlString).toURL();
        } catch (URISyntaxException | MalformedURLException exception) {
            System.out.println("URL errada!");
            System.exit(1);
        }
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        } catch (IOException exception) {
            System.out.println("Erro na requisição.");
            System.exit(2);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode resultMap;
        try {
            resultMap = objectMapper.readTree(result.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode typeNode = resultMap.get("types");
        this.type = PokeUtils.determinePokeType(typeNode.get(0).get("type").get("name").asText());
        if (typeNode.size() > 1) {
            this.secondType = PokeUtils.determinePokeType(typeNode.get(1).get("type").get("name").asText());
        }

        JsonNode statsNode = resultMap.get("stats");
        this.health = statsNode.get(0).get("base_stat").asInt();
        this.attack = statsNode.get(1).get("base_stat").asInt();
        this.defense = statsNode.get(2).get("base_stat").asInt();
        this.specialAttack = statsNode.get(3).get("base_stat").asInt();
        this.specialDefense = statsNode.get(4).get("base_stat").asInt();
        this.speed = statsNode.get(5).get("base_stat").asInt();

        this.imageBack = resultMap.get("sprites").get("back_default").asText();
        this.imageFront = resultMap.get("sprites").get("front_default").asText();

        char[] nameChars = name.toCharArray();
        nameChars[0] = Character.toUpperCase(nameChars[0]);
        this.name = new String(nameChars);
    }

}
