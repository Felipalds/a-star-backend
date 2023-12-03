package com.search.ai;

import com.search.Server;
import com.search.pokejava.Battle;
import com.search.pokejava.PokeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class PokeTree {

    private static Logger logger = LoggerFactory.getLogger(Server.class);
    PokeNode root;
    ArrayList<PokeNode> children;

    public static class PokeNode {
        public Battle battle;
        public float userHealth, aiHealth;
        int level = 0;
        public PokeNode parent;
        public ArrayList<PokeNode> children = new ArrayList<>();
        public int userMove, aiMove;
        public float f;
        public PokeNode(Battle battle, int userMove, int aiMove) {
            this.userHealth = battle.statusA.health;
            this.aiHealth = battle.statusB.health;
            this.userMove = userMove;
            this.aiMove = aiMove;
            this.battle = battle;
        }

        private float calculateG() {
            return parent.userHealth - userHealth;
        }

        // Logic here is inverse: we use the highest value, so we have to subtract H from G in final calculations.
        private float calculateH() {
            return userHealth;
        }

        public void setFValue() {

            f = parent != null ? calculateG() - calculateH() : 0f;

        }

        public void generateChildren() {
            if (children.isEmpty()) {
                int bestMove = -1;
                float highestDamage = -1f;
                for (int userI = 0; userI < 4; userI++) {
                    if (battle.pokemonA.moves[userI] != null) {
                        float damage = PokeUtils.calculateDamage(battle.statusA, battle.statusB, battle.pokemonA.moves[userI], 1f);
                        if (damage > highestDamage || bestMove == -1) {
                            bestMove = userI;
                            highestDamage = damage;
                        }
                    }
                }
                for (int aiI = 0; aiI < 4; aiI++) {
                    if (battle.pokemonB.moves[aiI] != null && !battle.ended) {
                        PokeNode pokeNode = new PokeNode(battle.makeTurn(bestMove, aiI), bestMove, aiI);
                        pokeNode.level = this.level + 1;
                        pokeNode.parent = this;
                        children.add(pokeNode);
                    }
                }
            } else {
                logger.warn("Tried to create children in a node with existing children.");
            }
        }
    }



    // F = G - H
    public PokeTree(Battle battle) {
        this.root = new PokeNode(battle, -1, -1);
        this.root.parent = null;
        this.root.generateChildren();
    }

    public void generateAllNodes() {
        for (PokeNode childNode : root.children) {
            if (childNode.aiHealth > 0 && childNode.userHealth > 0) {
                recursivelyGenerateAllNodes(childNode);
            }
        }
    }

    private static void recursivelyGenerateAllNodes(PokeNode node) {
        node.generateChildren();
        for (PokeNode childNode : node.children) {
            if (childNode.aiHealth > 0 && childNode.userHealth > 0) {
                recursivelyGenerateAllNodes(childNode);
            }
        }
    }

}
