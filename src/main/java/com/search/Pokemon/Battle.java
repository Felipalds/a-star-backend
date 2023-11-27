package Pokemon;

import Pokemon.Pokemon.DamageType;
import Pokemon.Pokemon.PokeType;

public class Battle {

    public static class PokeStatus {
        public float health, speed, attack, specialAttack, defense, specialDefense;
        public String name;
        public PokeType pokeType;
        public DamageType damageType;
        // Battle Conditions
        public boolean fainted = false;

        public PokeStatus(Pokemon pokemon) {
            this.health = pokemon.health;
            this.speed = pokemon.speed;
            this.specialAttack = pokemon.specialAttack;
            this.specialDefense = pokemon.specialDefense;
            this.attack = pokemon.attack;
            this.defense = pokemon.defense;
            this.pokeType = pokemon.type;
        }
    }

    public Pokemon pokemonA, pokemonB;
    public PokeStatus statusA, statusB;

    public Battle(Pokemon pokemonA, Pokemon pokemonB) {
        this.pokemonA = new Pokemon(pokemonA);
        this.pokemonB = new Pokemon(pokemonB);
        this.statusA = new PokeStatus(pokemonA);
        this.statusB = new PokeStatus(pokemonB);
    }

    private float getTypeEffectiveness(PokeType attack, PokeType target) {
        // TODO return Type Effectiveness.
        return 1.2f;
    }

    private void applyMove(PokeStatus statusAttacker, PokeStatus statusTarget, Move move) {
        // Calculate damage and apply
        float defense, attack;
        if (move.damageType == DamageType.SPECIAL) {
            defense = statusTarget.specialDefense;
            attack = statusAttacker.specialAttack;
        } else {
            defense = statusTarget.defense;
            attack = statusAttacker.attack;
        }
        float stab = 1f;
        if (move.pokeType == statusAttacker.pokeType) {
            stab = 1.5f;
        }
        float te = getTypeEffectiveness(statusAttacker.pokeType, statusTarget.pokeType);

        float damage = ((2f*move.power*(attack/defense))/50f+2)*stab;
    }

    // Move A is for pokemon A and B for pokemon B...
    public Battle makeTurn(int moveA, int moveB) {
        Battle nextTurn = new Battle(pokemonA, pokemonB);
        nextTurn.statusA = statusA;
        nextTurn.statusB = statusB;
        // Calculate result
        if (pokemonA.moves[moveA].priority > pokemonA.moves[moveB].priority) {
            applyMove(statusA, statusB, pokemonA.moves[moveA]);
            if (statusB.fainted == false) {
                applyMove(statusB, statusA, pokemonB.moves[moveB]);
            }
        } else if (pokemonA.moves[moveA].priority < pokemonA.moves[moveB].priority) {
            applyMove(statusB, statusA, pokemonB.moves[moveB]);
            if (statusB.fainted == false) {
                applyMove(statusA, statusB, pokemonA.moves[moveA]);
            }
        } else {
            if (pokemonA.speed > pokemonB.speed) {
                applyMove(statusA, statusB, pokemonA.moves[moveA]);
                if (statusB.fainted == false) {
                    applyMove(statusB, statusA, pokemonB.moves[moveB]);
                }
            } else if (pokemonA.speed <= pokemonB.speed) {
                applyMove(statusB, statusA, pokemonB.moves[moveB]);
                if (statusB.fainted == false) {
                    applyMove(statusA, statusB, pokemonA.moves[moveA]);
                }
            }
        }
        return nextTurn;
    }
}
