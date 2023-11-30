package com.search.pokejava;

import com.search.pokejava.types.PokeType;

import java.util.ArrayList;

public class Battle {

    public static class PokeStatus {
        public float getHealth() {
            return health;
        }

        public float getSpeed() {
            return speed;
        }

        public float getAttack() {
            return attack;
        }

        public float getSpecialAttack() {
            return specialAttack;
        }

        public float getDefense() {
            return defense;
        }

        public float getSpecialDefense() {
            return specialDefense;
        }

        public String getName() {
            return name;
        }

        public PokeType getPokeType() {
            return pokeType;
        }

        public PokeType getSecondPokeType() {
            return secondPokeType;
        }

        public int getSpeedStage() {
            return speedStage;
        }

        public int getAttackStage() {
            return attackStage;
        }

        public int getSpAttackStage() {
            return spAttackStage;
        }

        public int getDefenseStage() {
            return defenseStage;
        }

        public int getSpDefenseStage() {
            return spDefenseStage;
        }

        public boolean isFainted() {
            return fainted;
        }

        public float health, speed, attack, specialAttack, defense, specialDefense;
        public String name;
        public PokeType pokeType, secondPokeType;
        public int speedStage = 0, attackStage = 0, spAttackStage = 0, defenseStage = 0, spDefenseStage = 0;
        // Battle Conditions
        public boolean fainted = false;

        public PokeStatus() {

        }

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
            this.secondPokeType = pokeStatus.secondPokeType;
            this.speedStage = pokeStatus.speedStage;
            this.attackStage = pokeStatus.attackStage;
            this.defenseStage = pokeStatus.defenseStage;
            this.spAttackStage = pokeStatus.spAttackStage;
            this.spDefenseStage = pokeStatus.spDefenseStage;
            this.fainted = pokeStatus.fainted;
        }
    }

    public Pokemon pokemonA, pokemonB;
    public PokeStatus statusA, statusB;
    public int turn = 0;
    public boolean ended = false;
    public ArrayList<String> logs = new ArrayList<>();

    public ArrayList<String> getLogs() {
        return logs;
    }

    public Battle(Pokemon pokemonA, Pokemon pokemonB) {
        this.pokemonA = new Pokemon(pokemonA);
        this.pokemonB = new Pokemon(pokemonB);
        this.statusA = new PokeStatus(pokemonA);
        this.statusB = new PokeStatus(pokemonB);
    }

    public static float getTypeEffectiveness(PokeType attack, PokeType target) {
        return switch (attack) {
            case BUG -> {
                if (target == PokeType.FAIRY || target == PokeType.FIGHTER || target == PokeType.POISON
                        || target == PokeType.FLYING || target == PokeType.GHOST || target == PokeType.STEEL
                        || target == PokeType.FIRE) {
                    yield 0.5f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.DARK || target == PokeType.GRASS) {
                    yield 2f;
                }
                yield 1f;
            }
            case DARK -> {
                if (target == PokeType.FIGHTER || target == PokeType.DARK || target == PokeType.FAIRY) {
                    yield 0.5f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.GHOST) {
                    yield 2f;
                }
                yield 1f;
            }
            case DRAGON -> {
                if (target == PokeType.STEEL) {
                    yield 0.5f;
                } else if (target == PokeType.FAIRY) {
                    yield 0f;
                } else if (target == PokeType.DRAGON) {
                    yield 2f;
                }
                yield 1f;
            }
            case ELECTRIC -> {
                if (target == PokeType.DRAGON || target == PokeType.GRASS) {
                    yield 0.5f;
                } else if (target == PokeType.GROUND) {
                    yield 0f;
                } else if (target == PokeType.FLYING || target == PokeType.WATER) {
                    yield 2f;
                }
                yield 1f;
            }
            case FAIRY -> {
                if (target == PokeType.POISON || target == PokeType.FIRE || target == PokeType.STEEL) {
                    yield 0.5f;
                } else if (target == PokeType.FIGHTER || target == PokeType.DRAGON || target == PokeType.DARK) {
                    yield 2f;
                }
                yield 1f;
            }
            case FIGHTER -> {
                if (target == PokeType.BUG || target == PokeType.FLYING || target == PokeType.POISON
                        || target == PokeType.FAIRY || target == PokeType.PSYCHIC) {
                    yield 0.5f;
                } else if (target == PokeType.GHOST) {
                    yield 0f;
                } else if (target == PokeType.DARK || target == PokeType.STEEL || target == PokeType.ROCK
                        || target == PokeType.NORMAL || target == PokeType.ICE) {
                    yield 2f;
                }
                yield 1f;
            }
            case FIRE -> {
                if (target == PokeType.DRAGON || target == PokeType.FIRE || target == PokeType.WATER
                        || target == PokeType.ROCK) {
                    yield 0.5f;
                } else if (target == PokeType.ICE || target == PokeType.GRASS || target == PokeType.BUG
                        || target == PokeType.STEEL) {
                    yield 2f;
                }
                yield 1f;
            }
            case FLYING -> {
                if (target == PokeType.ROCK || target == PokeType.ELECTRIC || target == PokeType.STEEL) {
                    yield 0.5f;
                } else if (target == PokeType.FIGHTER || target == PokeType.GRASS || target == PokeType.BUG) {
                    yield 2f;
                }
                yield 1f;
            }
            case GHOST -> {
                if (target == PokeType.GHOST || target == PokeType.PSYCHIC) {
                    yield 2f;
                } else if (target == PokeType.DARK) {
                    yield 0.5f;
                } else if (target == PokeType.NORMAL) {
                    yield 0f;
                }
                yield 1f;
            }
            case GRASS -> {
                if (target == PokeType.WATER || target == PokeType.GROUND || target == PokeType.ROCK) {
                    yield 2f;
                } else if (target == PokeType.GRASS || target == PokeType.FIRE || target == PokeType.DRAGON
                        || target == PokeType.FLYING || target == PokeType.POISON || target == PokeType.STEEL) {
                    yield 0.5f;
                }
                yield 1f;
            }
            case GROUND -> {
                if (target == PokeType.FLYING) {
                    yield 0f;
                } else if (target == PokeType.BUG || target == PokeType.GRASS) {
                    yield 0.5f;
                } else if (target == PokeType.STEEL || target == PokeType.ROCK || target == PokeType.FIRE
                        || target == PokeType.POISON || target == PokeType.ELECTRIC) {
                    yield 2f;
                }
                yield 1f;
            }
            case ICE -> {
                if (target == PokeType.STEEL || target == PokeType.FIRE || target == PokeType.WATER
                        || target == PokeType.ICE) {
                    yield 0.5f;
                } else if (target == PokeType.FLYING || target == PokeType.GROUND || target == PokeType.GRASS
                        || target == PokeType.DRAGON) {
                    yield 2f;
                }
                yield 1f;
            }
            case NORMAL -> {
                if (target == PokeType.GHOST) {
                    yield 0f;
                } else if (target == PokeType.ROCK || target == PokeType.STEEL) {
                    yield 0.5f;
                }
                yield 1f;
            }
            case POISON -> {
                if (target == PokeType.POISON || target == PokeType.ROCK
                        || target == PokeType.GHOST) {
                    yield 0.5f;
                } else if (target == PokeType.STEEL) {
                    yield 0f;
                } else if (target == PokeType.GRASS || target == PokeType.FAIRY) {
                    yield 2f;
                }
                yield 1f;
            }
            case PSYCHIC -> {
                if (target == PokeType.POISON || target == PokeType.FIGHTER) {
                    yield 2f;
                } else if (target == PokeType.PSYCHIC || target == PokeType.STEEL) {
                    yield 0.5f;
                } else if (target == PokeType.DARK) {
                    yield 0f;
                }
                yield 1f;
            }
            case ROCK -> {
                if (target == PokeType.FIGHTER || target == PokeType.STEEL || target == PokeType.GROUND) {
                    yield 0.5f;
                } else if (target == PokeType.FLYING || target == PokeType.BUG || target == PokeType.FIRE
                        || target == PokeType.ICE) {
                    yield 2f;
                }
                yield 1f;
            }
            case STEEL -> {
                if (target == PokeType.STEEL || target == PokeType.WATER || target == PokeType.FIRE
                        || target == PokeType.ELECTRIC) {
                    yield 0.5f;
                } else if (target == PokeType.ICE || target == PokeType.FAIRY || target == PokeType.ROCK) {
                    yield 2f;
                }
                yield 1f;
            }
            case WATER -> {
                if (target == PokeType.WATER || target == PokeType.DRAGON || target == PokeType.GRASS) {
                    yield 0.5f;
                } else if (target == PokeType.GROUND || target == PokeType.ROCK || target == PokeType.FIRE) {
                    yield 2f;
                }
                yield 1f;
            }
        };
    }

    // Move A is for pokemon A and B for pokemon B...
    public Battle makeTurn(int moveA, int moveB) {
        Battle nextTurn = new Battle(pokemonA, pokemonB);
        nextTurn.turn = this.turn + 1;
        nextTurn.statusA = new PokeStatus(statusA);
        nextTurn.statusB = new PokeStatus(statusB);
        nextTurn.logs.add("=== Início do Turno [" + this.turn + "] ===");
        // Calculate result
        if (statusA.fainted || statusB.fainted) {
            nextTurn.logs.add("Tentativa de jogada. O jogo já acabou.");
            return this;
        }
        if (pokemonA.moves[moveA].priority > pokemonB.moves[moveB].priority) {
            nextTurn.logs.add(pokemonA.getName() + " usou " + pokemonA.moves[moveA].name + "!");
            pokemonA.moves[moveA].getEffect().apply(nextTurn, nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
            if (!nextTurn.statusB.fainted) {
                nextTurn.logs.add(pokemonB.getName() + " usou " + pokemonB.moves[moveB].name + "!");
                pokemonB.moves[moveB].getEffect().apply(nextTurn, nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
            } else {
                nextTurn.logs.add("O pokemon " + pokemonB.getName() + " caiu!");
            }
        } else if (pokemonA.moves[moveA].priority < pokemonB.moves[moveB].priority) {
            nextTurn.logs.add(pokemonB.getName() + " usou " + pokemonB.moves[moveB].name + "!");
            pokemonB.moves[moveB].getEffect().apply(nextTurn, nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
            if (!nextTurn.statusA.fainted) {
                nextTurn.logs.add(pokemonA.getName() + " usou " + pokemonA.moves[moveA].name + "!");
                pokemonA.moves[moveA].getEffect().apply(nextTurn, nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
            } else {
                nextTurn.logs.add("O pokemon " + pokemonA.getName() + " caiu!");
            }
        } else {
            if (pokemonA.speed >= pokemonB.speed) {
                nextTurn.logs.add(pokemonA.getName() + " usou " + pokemonA.moves[moveA].name + "!");
                pokemonA.moves[moveA].getEffect().apply(nextTurn, nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
                if (!nextTurn.statusB.fainted) {
                    nextTurn.logs.add(pokemonB.getName() + " usou " + pokemonB.moves[moveB].name + "!");
                    pokemonB.moves[moveB].getEffect().apply(nextTurn, nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
                } else {
                    nextTurn.logs.add("O pokemon " + pokemonB.getName() + " caiu!");
                }
            } else {
                nextTurn.logs.add(pokemonB.getName() + " usou " + pokemonB.moves[moveB].name + "!");
                pokemonB.moves[moveB].getEffect().apply(nextTurn, nextTurn.statusB, nextTurn.statusA, pokemonB.moves[moveB]);
                if (!nextTurn.statusA.fainted) {
                    nextTurn.logs.add(pokemonA.getName() + " usou " + pokemonA.moves[moveA].name + "!");
                    pokemonA.moves[moveA].getEffect().apply(nextTurn, nextTurn.statusA, nextTurn.statusB, pokemonA.moves[moveA]);
                } else {
                    nextTurn.logs.add("O pokemon " + pokemonA.getName() + " caiu!");
                }
            }
        }
        if (nextTurn.statusA.fainted || nextTurn.statusB.fainted) {
            nextTurn.logs.add("A batalha encerra!");
            if (nextTurn.statusA.fainted && !nextTurn.statusB.fainted) {
                nextTurn.logs.add(pokemonB.getName() + " é vitorioso!");
            } else if (!nextTurn.statusA.fainted) {
                nextTurn.logs.add(pokemonA.getName() + " é vitorioso!");
            } else {
                nextTurn.logs.add("É um empate!");
            }
            nextTurn.ended = true;
        }
        return nextTurn;
    }
}
