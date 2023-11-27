import Pokemon.Battle;
import Pokemon.Move;
import Pokemon.Pokemon;

public class Main {

    private static Pokemon getPikachu() {
        Pokemon pikachu = new Pokemon("pikachu");
        pikachu.moves[0] = new Move("tackle");
        pikachu.moves[1] = new Move("thunder-shock");
        return pikachu;
    }

    private static Pokemon getSquirtle() {
        Pokemon squirtle = new Pokemon("squirtle");
        squirtle.moves[0] = new Move("tackle");
        squirtle.moves[1] = new Move("bubble");
        return squirtle;
    }

    // Splits first occurence.
    public static void main(String[] args) {
        Pokemon pikachu = getPikachu();
        Pokemon squirtle = getSquirtle();
        Battle battle = new Battle(pikachu, squirtle);
    }
}