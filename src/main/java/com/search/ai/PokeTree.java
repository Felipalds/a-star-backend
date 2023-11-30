package com.search.ai;

import com.search.Server;
import com.search.pokejava.Battle;
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
        public PokeNode(Battle battle, int userMove, int aiMove) {
            this.userHealth = battle.statusA.health;
            this.aiHealth = battle.statusB.health;
            this.userMove = userMove;
            this.aiMove = aiMove;
            this.battle = battle;
        }

        public void generateChildren() {
            if (children.isEmpty()) {
                for (int userMove = 0; userMove < 4; userMove++) {
                    for (int aiMove = 0; aiMove < 4; aiMove++) {
                        if (battle.pokemonA.moves[userMove] != null && battle.pokemonB.moves[aiMove] != null) {
                            PokeNode pokeNode = new PokeNode(battle.makeTurn(userMove, aiMove), userMove, aiMove);
                            pokeNode.level = this.level + 1;
                            pokeNode.parent = this;
                            children.add(pokeNode);
                            System.out.println(battle.pokemonA.moves[userMove].name + " " + battle.pokemonB.moves[aiMove].name);
                            System.out.println(pokeNode.userHealth + " " + pokeNode.aiHealth);
                            System.out.println(pokeNode.level);
                        }
                    }
                }
            } else {
                logger.warn("Tried to create children in a node with existing children.");
            }
        }
    }

    private float calculateG(PokeNode childNode) {
        float damageDealt = childNode.userHealth - childNode.parent.userHealth;
        float healthRecovered = childNode.parent.aiHealth - childNode.aiHealth;
        return damageDealt + healthRecovered;
    }

    // Logic here is inverse: we use the highest value, so we have to subtract H from G in final calculations.
    private float calculateH(PokeNode childNode) {
        return childNode.userHealth + (this.root.aiHealth - childNode.aiHealth);
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
