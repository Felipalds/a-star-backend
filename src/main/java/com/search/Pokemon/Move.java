package Pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import Pokemon.Pokemon.DamageType;
import Pokemon.Pokemon.PokeType;

public class Move {
    public String name;
    public int priority;
    public float power;
    public DamageType damageType;
    public PokeType pokeType;

    public Move(Move move) {
        this.name = move.name;
        this.priority = move.priority;
        this.power = move.power;
        this.damageType = move.damageType;
        this.pokeType = move.pokeType;
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
        // Power
        String cutString = PokeUtils.stringSplit(result.toString(), "\"power\":").get(1);
        ArrayList<String> cutArr = PokeUtils.stringSplit(cutString, ",");
        this.power = Float.parseFloat(cutArr.get(0));
        // Priority
        cutString = PokeUtils.stringSplit(cutArr.get(1), "\"priority\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        this.priority = Integer.parseInt(cutArr.get(0));
        // Type
        cutString = PokeUtils.stringSplit(cutArr.get(1), "\"type\":").get(1);
        cutArr = PokeUtils.stringSplit(cutString, ",");
        String type = PokeUtils.stringSplit(cutArr.get(0), "\"name\":\"").get(1);
        type = type.substring(0, type.length() - 1);
        if (type.equals("electric")) {
            this.pokeType = PokeType.ELECTRIC;
        }
        // Damage Class
        String effectString = PokeUtils.stringSplit(result.toString(), "\"damage_class\"").get(1);
        cutArr = PokeUtils.stringSplit(effectString, ",");
        type = PokeUtils.stringSplit(cutArr.get(0), ":{\"name\":\"").get(1);
        type = type.substring(0, type.length() - 1);
        if (type.equals("physical")) {
            this.damageType = DamageType.PHYSICAL;
        } else if (type.equals("special")) {
            this.damageType = DamageType.SPECIAL;
        }
        this.name = name;
    }
}
