package com.search.pokejava;

import java.util.ArrayList;
import com.search.pokejava.types.PokeType;

public class PokeUtils {
    public static ArrayList<String> stringSplit(String string, String delimiter) {
        ArrayList<String> result = new ArrayList<>();
        int index = string.indexOf(delimiter);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            result.add("");
            result.add(string.substring(index + delimiter.length(), string.length() ));
        } else if (index == string.length() - 1) {
            result.add(string.substring(0, index + delimiter.length()));
            result.add("");
        } else {
            result.add(string.substring(0, index));
            result.add(string.substring(index + delimiter.length(), string.length()));
        }
        return result;
    }

    public static PokeType determinePokeType(String pokeTypeString) {
        PokeType pokeType = PokeType.NORMAL;
        if (pokeTypeString.equals("electric")) {
            pokeType = PokeType.ELECTRIC;
        } else if (pokeTypeString.equals("water")) {
            pokeType = PokeType.WATER;
        } else if (pokeTypeString.equals("ghost")) {
            pokeType = PokeType.GHOST;
        } else if (pokeTypeString.equals("fire")) {
            pokeType = PokeType.FIRE;
        } else if (pokeTypeString.equals("fairy")) {
            pokeType = PokeType.FAIRY;
        } else if (pokeTypeString.equals("fighting")) {
            pokeType = PokeType.FIGHTER;
        } else if (pokeTypeString.equals("dark")) {
            pokeType = PokeType.DARK;
        } else if (pokeTypeString.equals("dragon")) {
            pokeType = PokeType.DRAGON;
        } else if (pokeTypeString.equals("poison")) {
            pokeType = PokeType.POISON;
        } else if (pokeTypeString.equals("flying")) {
            pokeType = PokeType.FLYING;
        } else if (pokeTypeString.equals("ground")) {
            pokeType = PokeType.GROUND;
        } else if (pokeTypeString.equals("rock")) {
            pokeType = PokeType.ROCK;
        } else if (pokeTypeString.equals("psychic")) {
            pokeType = PokeType.PSYCHIC;
        } else if (pokeTypeString.equals("grass")) {
            pokeType = PokeType.GRASS;
        } else if (pokeTypeString.equals("ice")) {
            pokeType = PokeType.ICE;
        } else if (pokeTypeString.equals("bug")) {
            pokeType = PokeType.BUG;
        } else if (pokeTypeString.equals("steel")) {
            pokeType = PokeType.STEEL;
        }
        return pokeType;
    }
}
