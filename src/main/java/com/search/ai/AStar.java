package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;

public class AStar {

    private ArrayList<PokeTree.PokeNode> openList = new ArrayList<>();
    private ArrayList<PokeTree.PokeNode> closedList = new ArrayList<>();
    private PokeTree.PokeNode goal = null;
    public int lostStates = 0;
    public boolean stoppedToLost = false;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public long start, finish, timeElapsed;

    public AStar(Battle battle) {
        start = System.currentTimeMillis();
        PokeTree pokeTree = new PokeTree(battle);
        // Iniciar uma lista aberta com o root. O valor F do root éé sempre 0.
        openList.add(pokeTree.root);
        pokeTree.root.setFValue();

        while (!openList.isEmpty() && goal == null && lostStates < 300000) {
            // Descobrir o nó Q com o maior valor F na lista aberta.
            int highestF = 0;
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).f > openList.get(highestF).f) {
                    highestF = i;
                }
            }

            // Remover o Q da lista aberta e gerar seus sucessores.
            PokeTree.PokeNode q = openList.remove(highestF);
//            System.out.println(q.userHealth + " " + q.aiHealth);
            if (q.aiHealth <= 0f) {
                lostStates++;
            }
            if (q.userHealth <= 0f && q.aiHealth > 0f) {
                goal = q;
                break;
            }
            q.generateChildren();
            for (PokeTree.PokeNode qChild : q.children) {
                qChild.setFValue();
            }
            //                System.out.println("====================================\n\n");
            //                System.out.println(q.children.indexOf(qChild) + ": " + battle.pokemonA.moves[qChild.userMove].name + " " + battle.pokemonB.moves[qChild.aiMove].name);
            //                System.out.println("HPA: " + qChild.userHealth + " HTPB: " + qChild.aiHealth);
            openList.addAll(q.children);
            closedList.add(q);
        }
        if (lostStates >= 300000) {
            this.stoppedToLost = true;
        }
        if (goal != null) {
            path.addFirst(goal);
            PokeTree.PokeNode parent = goal.parent;
            while (parent != null) {
//                if (parent.aiMove != -1) {
//                    System.out.println(battle.pokemonB.moves[parent.aiMove].name);
//                }
                path.addFirst(parent);
                parent = parent.parent;
            }
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("A* terminou em: " + timeElapsed/1000f + " segundos.");
    }
}
