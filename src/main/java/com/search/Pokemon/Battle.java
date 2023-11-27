package Pokemon;

import Pokemon.Pokemon.DamageType;
import Pokemon.Pokemon.PokeType;

public class Battle {

    public static class PokeStatus {
        public float health, speed, attack, specialAttack, defense, specialDefense;
        public String name;
        public PokeType pokeType, secondPokeType;
        public DamageType damageType;
        // Battle Conditions
        public boolean fainted = false;

        public PokeStatus(Pokemon pokemon) {
            this.name = pokemon.name;
            this.health = pokemon.health;
            this.speed = pokemon.speed;
            this.specialAttack = pokemon.specialAttack;
            this.specialDefense = pokemon.specialDefense;
            this.attack = pokemon.attack;
            this.defense = pokemon.defense;
            this.pokeType = pokemon.type;
            this.secondPokeType = pokemon.secondType;
        }

        public PokeStatus(PokeStatus pokeStatus) {
            this.name = pokeStatus.name;
            this.health = pokeStatus.health;
            this.speed = pokeStatus.speed;
            this.specialAttack = pokeStatus.specialAttack;
            this.specialDefense = pokeStatus.specialDefense;
            this.attack = pokeStatus.attack;
            this.defense = pokeStatus.defense;
            this.pokeType = pokeStatus.pokeType;
        }
    }

    public Pokemon pokemonA, pokemonB;
    public PokeStatus statusA, statusB;
    public int turn = 0;

    public Battle(Pokemon pokemonA, Pokemon pokemonB) {
        this.pokemonA = new Pokemon(pokemonA);
        this.pokemonB = new Pokemon(pokemonB);
        this.statusA = new PokeStatus(pokemonA);
        this.statusB = new PokeStatus(pokemonB);
    }

    private float getTypeEffectiveness(PokeType attack, PokeType target) {
        switch (attack) {
            case BUG:
                if (target == PokeType.FAIRY || target == PokeType.FIGHTER || target == PokeType.POISON
                        || target == PokeType.FLYING || target == PokeType.GHOST || target == PokeType.STEEL
                        || target == PokeType.FIRE) {
                    return 0.5f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.DARK || target == PokeType.GRASS) {
                    return 2f;
                }
                return 1f;
            case DARK:
                if (target == PokeType.FIGHTER || target == PokeType.DARK || target == PokeType.FAIRY) {
                    return 0.5f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.GHOST) {
                    return 2f;
                }
                return 1f;
            case DRAGON:
                if (target == PokeType.STEEL) {
                    return 0.5f;
                } else if (target == PokeType.FAIRY) {
                    return 0f;
                } else if (target == PokeType.DRAGON) {
                    return 2f;
                }
                return 1f;
            case ELECTRIC:
                if (target == PokeType.GRASS || target == PokeType.DRAGON || target == PokeType.GRASS) {
                    return 0.5f;
                } else if (target == PokeType.GROUND) {
                    return 0f;
                } else if (target == PokeType.FLYING || target == PokeType.WATER) {
                    return 2f;
                }
                return 1f;
            case FAIRY:
                if (target == PokeType.POISON || target == PokeType.FIRE || target == PokeType.STEEL) {
                    return 0.5f;
                } else if (target == PokeType.FIGHTER || target == PokeType.DRAGON || target == PokeType.DARK) {
                    return 2f;
                }
                return 1f;
            case FIGHTER:
                if (target == PokeType.BUG || target == PokeType.FLYING || target == PokeType.POISON
                        || target == PokeType.FAIRY || target == PokeType.PSYCHIC) {
                    return 0.5f;
                } else if (target == PokeType.GHOST) {
                    return 0f;
                } else if (target == PokeType.DARK || target == PokeType.STEEL || target == PokeType.ROCK
                        || target == PokeType.NORMAL || target == PokeType.ICE) {
                    return 2f;
                }
                return 1f;
            case FIRE:
                if (target == PokeType.DRAGON || target == PokeType.FIRE || target == PokeType.WATER
                        || target == PokeType.ROCK) {
                    return 0.5f;
                } else if (target == PokeType.ICE || target == PokeType.GRASS || target == PokeType.BUG
                        || target == PokeType.STEEL) {
                    return 2f;
                }
                return 1f;
            case FLYING:
                if (target == PokeType.ROCK || target == PokeType.ELECTRIC || target == PokeType.STEEL) {
                    return 0.5f;
                } else if (target == PokeType.FIGHTER || target == PokeType.GRASS || target == PokeType.BUG) {
                    return 2f;
                }
                return 1f;
            case GHOST:
                if (target == PokeType.GHOST || target == PokeType.PSYCHIC) {
                    return 2f;
                } else if (target == PokeType.DARK) {
                    return 0.5f;
                } else if (target == PokeType.NORMAL) {
                    return 0f;
                }
                return 1f;
            case GRASS:
                if (target == PokeType.WATER || target == PokeType.GROUND || target == PokeType.ROCK) {
                    return 2f;
                } else if (target == PokeType.GRASS || target == PokeType.FIRE || target == PokeType.DRAGON
                        || target == PokeType.FLYING || target == PokeType.POISON || target == PokeType.STEEL) {
                    return 0.5f;
                }
                return 1f;
            case GROUND:
                if (target == PokeType.FLYING) {
                    return 0f;
                } else if (target == PokeType.BUG || target == PokeType.GRASS) {
                    return 0.5f;
                } else if (target == PokeType.STEEL || target == PokeType.ROCK || target == PokeType.FIRE
                        || target == PokeType.POISON || target == PokeType.ELECTRIC) {
                    return 2f;
                }
                return 1f;
            case ICE:
                if (target == PokeType.STEEL || target == PokeType.FIRE || target == PokeType.WATER
                        || target == PokeType.ICE) {
                    return 0.5f;
                } else if (target == PokeType.FLYING || target == PokeType.GROUND || target == PokeType.GRASS
                        || target == PokeType.DRAGON) {
                    return 2f;
                }
                return 1f;
            case NORMAL:
                if (target == PokeType.GHOST) {
                    return 0f;
                } else if (target == PokeType.ROCK || target == PokeType.STEEL) {
                    return 0.5f;
                }
                return 1f;
            case POISON:
                if (target == PokeType.POISON || target == PokeType.POISON || target == PokeType.ROCK
                        || target == PokeType.GHOST) {
                    return 0.5f;
                } else if (target == PokeType.STEEL) {
                    return 0f;
                } else if (target == PokeType.GRASS || target == PokeType.FAIRY) {
                    return 2f;
                }
                return 1f;
            case PSYCHIC:
                if (target == PokeType.POISON || target == PokeType.FIGHTER) {
                    return 2f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.STEEL) {
                    return 0.5f;
                } else if (target == PokeType.DARK) {
                    return 0f;
                }
                return 1f;
            case ROCK:
                if (target == PokeType.FIGHTER || target == PokeType.STEEL || target == PokeType.GROUND) {
                    return 0.5f;
                } else if (target == PokeType.FLYING || target == PokeType.BUG || target == PokeType.FIRE
                        || target == PokeType.ICE) {
                    return 2f;
                }
                return 1f;
            case STEEL:
                if (target == PokeType.STEEL || target == PokeType.WATER || target == PokeType.FIRE
                        || target == PokeType.ELECTRIC) {
                    return 0.5f;
                } else if (target == PokeType.ICE || target == PokeType.FAIRY || target == PokeType.ROCK) {
                    return 2f;
                }
                return 1f;
            case WATER:
                if (target == PokeType.WATER || target == PokeType.DRAGON || target == PokeType.GRASS) {
                    return 0.5f;
                } else if (target == PokeType.GROUND || target == PokeType.ROCK || target == PokeType.FIRE) {
                    return 2f;
                }
                return 1f;
            default:
                return 1f;

        }
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
        float te = getTypeEffectiveness(move.pokeType, statusTarget.pokeType);
        float te2 = getTypeEffectiveness(move.pokeType, statusTarget.secondPokeType);
        float damage = ((2f * move.power * (attack / defense)) / 50f + 2) * stab * te * te2 * 1.125f;
        System.out.println(statusAttacker.name + " usou " + move.name + "!");
        System.out.println(statusTarget.name + " recebe " + damage + " de dano!");

        float previousHealth = statusTarget.health;

        statusTarget.health -= damage;
        if (statusTarget.health <= 0) {
            statusTarget.health = 0;
            statusTarget.fainted = true;
        }

        if (te == 2f && te2 == 2f) {
            System.out.println("Super efetivo! x4");
        } else if (te == 2f || te2 == 2f) {
            System.err.println("Super efetivo! x2");
        } else if (te == 0.5f && te2 == 0.5f) {
            System.err.println("Oponente resiste ao ataque! x0.25");
        } else if (te == 0.5f || te2 == 0.5f) {
            System.err.println("Oponente resiste ao ataque! x0.5");
        } else if (te == 0f || te2 == 0f) {
            System.err.println("Sem efeito no oponente! x0");
        }
        System.out.println("HP: " + previousHealth + " -> " + statusTarget.health);
        if (statusTarget.health <= 0) {
            System.err.println("Oponente caiu!");
        }
        System.err.println("=================\n");
    }

    // Move A is for pokemon A and B for pokemon B...
    public Battle makeTurn(int moveA, int moveB) {
        Battle nextTurn = new Battle(pokemonA, pokemonB);
        nextTurn.turn = this.turn + 1;
        nextTurn.statusA = new PokeStatus(statusA);
        nextTurn.statusB = new PokeStatus(statusB);
        System.err.println("=== Turno [" + this.turn + "] ===\n");
        if (statusA.fainted == true) {
            System.err.println("Oponente A vitorioso.");
            return this;
        } else if (statusB.fainted == true) {
            System.err.println("Oponente B vitorioso");
            return this;
        }
        // Calculate result
        if (pokemonA.moves[moveA].priority > pokemonB.moves[moveB].priority) {
            applyMove(nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
            if (nextTurn.statusB.fainted == false) {
                applyMove(nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
            }
        } else if (pokemonA.moves[moveA].priority < pokemonB.moves[moveB].priority) {
            applyMove(nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
            if (nextTurn.statusB.fainted == false) {
                applyMove(nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
            }
        } else {
            if (pokemonA.speed > pokemonB.speed) {
                applyMove(nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
                if (nextTurn.statusB.fainted == false) {
                    applyMove(nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
                }
            } else if (pokemonA.speed <= pokemonB.speed) {
                applyMove(nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
                if (nextTurn.statusB.fainted == false) {
                    applyMove(nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
                }
            }
        }
        return nextTurn;
    }
}
