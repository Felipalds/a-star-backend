package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private final Queue<PokeTree.PokeNode> queue = new LinkedList<>();
    private PokeTree.PokeNode goal, bestNode;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public int iterations = 0;
    public long start, finish, timeElapsed;
    private boolean finished = false;
    public boolean stoppedToLost = false;

    private void traverse(PokeTree.PokeNode node) {
        if (node.children.isEmpty()) {
            node.generateChildren();
        }
        for (PokeTree.PokeNode child : node.children) {
            queue.add(child);
            if (bestNode == null || (child.userHealth < bestNode.userHealth)) {
                bestNode = child;
            }
            iterations++;
            if (child.userHealth <= 0f) {
                finished = true;
                goal = child;
                return;
            }
        }
    }
    public BFS(Battle battle) {
        start = System.currentTimeMillis();
        PokeTree pokeTree = new PokeTree(battle);
        if (pokeTree.root.userHealth <= 0f) {
            goal = pokeTree.root;
            path.addFirst(goal);
        } else {
            traverse(pokeTree.root);
            while (!finished && (System.currentTimeMillis() - start) < 3000) {
                traverse(queue.remove());
                System.out.println(iterations);
                if (queue.isEmpty()) {
                    break;
                }
            }
        }
        if (goal == null || (System.currentTimeMillis() - start) >= 3000) {
            System.out.println("BFS: Could not reach goal. Chose best Node encountered so far.");
            this.stoppedToLost = true;
            path.addFirst(bestNode);
            PokeTree.PokeNode parent = bestNode.parent;
            while (parent != null) {
                path.addFirst(parent);
                parent = parent.parent;
            }
        } else if (goal != null) {
            path.addFirst(goal);
            PokeTree.PokeNode parent = bestNode.parent;
            while (parent != null) {
                path.addFirst(parent);
                parent = parent.parent;
            }
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("BFS terminou em: " + timeElapsed/1000f + " segundos.");
    }
}
