package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;

public class AStar {

    private ArrayList<PokeTree.PokeNode> openList = new ArrayList<>();
    private ArrayList<PokeTree.PokeNode> closedList = new ArrayList<>();
    private PokeTree.PokeNode goal = null;
    public int iterations = 0;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public long start, finish, timeElapsed;

    public AStar(Battle battle) {
        start = System.currentTimeMillis();
        PokeTree pokeTree = new PokeTree(battle);
        // Iniciar uma lista aberta com o root. O valor F do root éé sempre 0.
        openList.add(pokeTree.root);
        while (!openList.isEmpty() && goal == null) {
            iterations++;
            // Descobrir o nó Q com o maior valor F na lista aberta.
            int highestF = 0;
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).f > openList.get(highestF).f) {
                    highestF = i;
                }
            }

            // Remover o Q da lista aberta e gerar seus sucessores.
            PokeTree.PokeNode q = openList.remove(highestF);
            if (q.children.isEmpty()) {
                q.generateChildren();
            }

            // Para cada sucessor
            for (PokeTree.PokeNode child : q.children) {
                if (child.userHealth <= 0f) { // Se for o final, parar
                    goal = child;
                    break;
                }
                boolean samePositionOpenLowerF = false;
                for (PokeTree.PokeNode openNode : openList) {
                    if (openNode.level == child.level && openNode.f > child.f) {
                        samePositionOpenLowerF = true;
                        break;
                    }
                }
                if (samePositionOpenLowerF) continue;
                boolean samePositionClosedLowerF = false;
                for (PokeTree.PokeNode openNode : openList) {
                    if (openNode.level == child.level && openNode.f > child.f) {
                        samePositionClosedLowerF = true;
                        break;
                    }
                }
                if (samePositionClosedLowerF) continue;
                openList.add(child);
            }
            closedList.add(q);
        }
        if (goal != null) {
            path.addFirst(goal);
            PokeTree.PokeNode parent = goal.parent;
            while (parent != null) {
                path.addFirst(parent);
                parent = parent.parent;
            }
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("A* terminou em: " + timeElapsed/60f + " segundos.");
    }
}
