package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;

public class AStar {

    private ArrayList<PokeTree.PokeNode> openList = new ArrayList<>();
    private ArrayList<PokeTree.PokeNode> closedList = new ArrayList<>();
    private PokeTree.PokeNode goal = null, bestNode;
    public int lostStates = 0;
    public boolean stoppedToLost = false;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public long start, finish, timeElapsed;

    public AStar(Battle battle) {
        start = System.currentTimeMillis();
        PokeTree pokeTree = new PokeTree(battle);
        // Iniciar uma lista aberta com o root. O valor F do root éé sempre 0.
        openList.add(pokeTree.root);
        bestNode = null;
        pokeTree.root.f = (float) Double.NEGATIVE_INFINITY;

        while (!openList.isEmpty() && goal == null && (System.currentTimeMillis() - start) < 3000f) {
            // Descobrir o nó Q com o maior valor F na lista aberta.
            int highestF = 0;
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).f > openList.get(highestF).f) {
                    highestF = i;
                }
            }

            // Remover o Q da lista aberta e gerar seus sucessores.
            PokeTree.PokeNode q = openList.remove(highestF);

            if (q.userHealth <= 0f) {
                goal = q;
                break;
            }
            q.generateChildren();
            for (PokeTree.PokeNode qChild : q.children) {
                qChild.setFValue();
            }
            if (bestNode == null || q.f > bestNode.f) {
                bestNode = q;
            }
            openList.addAll(q.children);
            closedList.add(q);
        }
        if (goal == null) {
            System.out.println("A*: Could not reach goal. Chose best Node encountered so far.");
            if (bestNode.aiMove == -1) {
                System.out.println("Batalha ja man!???");
                System.out.println("Best Node's Parent: " + bestNode.parent);
            }
            this.stoppedToLost = true;
            path.addFirst(bestNode);
            PokeTree.PokeNode parent = bestNode.parent;
            while (parent != null) {
                path.addFirst(parent);
                parent = parent.parent;
            }
        } else {
            path.addFirst(goal);
            PokeTree.PokeNode parent = goal.parent;
            while (parent != null) {
                path.addFirst(parent);
                parent = parent.parent;
            }
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("A* terminou em: " + timeElapsed/1000f + " segundos.");
    }
}
