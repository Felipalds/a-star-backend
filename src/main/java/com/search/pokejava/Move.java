package com.search.pokejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.pokejava.types.DamageType;
import com.search.pokejava.types.PokeType;

public class Move {
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public float getPower() {
        return power;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public PokeType getPokeType() {
        return pokeType;
    }

    public String name, description;
    public int priority;
    public float power;
    public DamageType damageType;
    public PokeType pokeType;

    public Move() {

    }
    public Move(Move move) {
        this.name = move.name;
        this.priority = move.priority;
        this.power = move.power;
        this.damageType = move.damageType;
        this.pokeType = move.pokeType;
        this.description = move.description;
    }

    @JsonIgnore
    public Effect getEffect() {
        return Effect.identifyEffect(this.name);
    }

    public Move(String name) {
        String urlString = "https://pokeapi.co/api/v2/move/" + name;
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

        ObjectMapper mapper = new ObjectMapper();
        JsonNode resultMap;

        try {
            resultMap = mapper.readTree(result.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Damage Class
        switch (resultMap.get("damage_class").get("name").asText()) {
            case "physical":
                this.damageType = DamageType.PHYSICAL;
                break;
            case "special":
                this.damageType = DamageType.SPECIAL;
                break;
            default:
                this.damageType = DamageType.STATUS;
        }

        // Description
        JsonNode effectNode = resultMap.get("effect_entries").get(0).get("effect");
        this.description = effectNode.asText();

        // Power
        this.power = resultMap.get("power").asInt();

        // Priority
        this.priority = resultMap.get("priority").asInt();

        // Type
        this.pokeType = PokeUtils.determinePokeType(resultMap.get("type").get("name").asText());

        // Capitalize
        char[] nameChars = name.toCharArray();
        nameChars[0] = Character.toUpperCase(nameChars[0]);
        this.name = new String(nameChars);
    }

    
}
