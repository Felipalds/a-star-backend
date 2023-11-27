package com.search.pokejava;

public interface Effect {

    // Multiplier = max(2, 2 + s)/max(2, 2 - s)
     static Effect identifyEffect(String effect) {
        if (effect.equals("Raises the user's Attack by two stages.")) {
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
        } else if (effect.equals("Raises the user's Attack by one stage.")) {
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
        } else 
        return null;
    }

    void apply(Battle.PokeStatus statusAttacker, Battle.PokeStatus statusTarget, Move move);
    
}
