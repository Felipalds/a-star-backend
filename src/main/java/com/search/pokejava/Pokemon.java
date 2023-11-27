package com.search.pokejava;


import com.search.pokejava.types.PokeType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

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
        int startIndex = result.indexOf("\"stats\"");
        String statsString = result.substring(startIndex);
        statsString = statsString.substring(0, statsString.indexOf("]") + 1);
        this.setStats(statsString);
        char[] nameChars = name.toCharArray();
        nameChars[0] = Character.toUpperCase(nameChars[0]);
        this.name = new String(nameChars);
        String cutString = PokeUtils.stringSplit(result.toString(), "\"type\":").get(1);
        ArrayList<String> cutArr = PokeUtils.stringSplit(cutString, "\"name\":\"");
        String firsType = PokeUtils.stringSplit(cutArr.get(1), "\"").get(0);
        this.type = PokeUtils.determinePokeType(firsType);
        try {
            cutArr = PokeUtils.stringSplit(PokeUtils.stringSplit(cutArr.get(1), "\"type\"").get(1), "\"name\":\"");
            String secondType = PokeUtils.stringSplit(cutArr.get(1), "\"").get(0);
            this.secondType = PokeUtils.determinePokeType(secondType);
        } catch (IndexOutOfBoundsException exception) {
            this.secondType = null;
        }
        cutString = PokeUtils.stringSplit(result.toString(), "\"back_default\":\"").get(1);
        cutArr = PokeUtils.stringSplit(cutString, "\"");
        this.imageBack = cutArr.get(0);
        cutString = PokeUtils.stringSplit(cutArr.get(1), "\"front_default\":\"").get(1);
        this.imageFront = PokeUtils.stringSplit(cutString, "\"").get(0);
    }

    private void setStats(String statsString) {
        // Health
        String cutString = PokeUtils.stringSplit(statsString, "\"base_stat\":").get(1);
        ArrayList<String> cutArr = PokeUtils.stringSplit(cutString, ",");
        this.health = Float.parseFloat(cutArr.get(0));
        // Attack
        cutString = PokeUtils.stringSplit(cutString, "\"base_stat\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.attack = Float.parseFloat(cutArr.get(0));
        // Defense
        cutString = PokeUtils.stringSplit(cutString, "\"base_stat\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.defense = Float.parseFloat(cutArr.get(0));
        // Special Attack
        cutString = PokeUtils.stringSplit(cutString, "\"base_stat\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.specialAttack = Float.parseFloat(cutArr.get(0));
        // Special Defense
        cutString = PokeUtils.stringSplit(cutString, "\"base_stat\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.specialDefense = Float.parseFloat(cutArr.get(0));
        // Speed
        cutString = PokeUtils.stringSplit(cutString, "\"base_stat\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.speed = Float.parseFloat(cutArr.get(0));
    }

}
