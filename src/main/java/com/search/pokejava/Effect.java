package com.search.pokejava;

public interface Effect {

    // Multiplier = max(2, 2 + s)/max(2, 2 - s)
    static Effect identifyEffect(String effect) {

        if (effect.contains("Raises the user's Attack by two stages.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.attackStage == 6) {
                    System.err.println("O ataque não pode mais aumentar!");
                } else {
                    System.err.println("O ataque sobe em dois estágios!");
                    statusAttacker.attackStage += 2;
                    if (statusAttacker.attackStage > 6) {
                        statusAttacker.attackStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Attack by one stage.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.attackStage == 6) {
                    System.err.println("O ataque não pode mais aumentar!");
                } else {
                    System.err.println("O ataque sobe em um estágio!");
                    statusAttacker.attackStage += 1;
                    if (statusAttacker.attackStage > 6) {
                        statusAttacker.attackStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Defense by one stage.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.defenseStage == 6) {
                    System.err.println("A defesa não pode mais aumentar!");
                } else {
                    System.err.println("A defesa sobe em um estágio!");
                    statusAttacker.defenseStage += 1;
                    if (statusAttacker.defenseStage > 6) {
                        statusAttacker.defenseStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Defense by two stages.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.defenseStage == 6) {
                    System.err.println("A defesa não pode mais aumentar!");
                } else {
                    System.err.println("A defesa sobe em dois estágios!");
                    statusAttacker.defenseStage += 2;
                    if (statusAttacker.defenseStage > 6) {
                        statusAttacker.defenseStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Special Attack by two stages.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.spAttackStage == 6) {
                    System.err.println("O ataque especial não pode mais aumentar!");
                } else {
                    System.err.println("O ataque especial sobe em dois estágios!");
                    statusAttacker.spAttackStage += 2;
                    if (statusAttacker.spAttackStage > 6) {
                        statusAttacker.spAttackStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Special Defense by one stage.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.spDefenseStage == 6) {
                    System.err.println("A defesa especial não pode mais aumentar!");
                } else {
                    System.err.println("A defesa especial em um estágio!");
                    statusAttacker.spDefenseStage += 1;
                    if (statusAttacker.spDefenseStage > 6) {
                        statusAttacker.spDefenseStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Special Attack by one stage.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.spAttackStage == 6) {
                    System.err.println("O ataque especial não pode mais aumentar!");
                } else {
                    System.err.println("O ataque especial em um estágio!");
                    statusAttacker.spAttackStage += 1;
                    if (statusAttacker.spAttackStage > 6) {
                        statusAttacker.spAttackStage = 6;
                    }
                }
            };
        } else if (effect.contains("Raises the user's Special Defense by two stages.")) {
            return (statusAttacker, statusTarget, move) -> {
                if (statusAttacker.spDefenseStage == 6) {
                    System.err.println("A defesa especial não pode mais aumentar!");
                } else {
                    System.err.println("A defesa especial sobe em dois estágios!");
                    statusAttacker.spDefenseStage += 2;
                    if (statusAttacker.spDefenseStage > 6) {
                        statusAttacker.spDefenseStage = 6;
                    }
                }
            };
        }
        return null;
    }

    void apply(Battle.PokeStatus statusAttacker, Battle.PokeStatus statusTarget, Move move);

}
