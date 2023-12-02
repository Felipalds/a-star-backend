package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private final Queue<PokeTree.PokeNode> queue = new LinkedList<>();
    private PokeTree.PokeNode front;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public int lostStates = 0;
    public long start, finish, timeElapsed;
    private boolean finished = false;
    public boolean stoppedToLost = false;

    private void traverse(PokeTree.PokeNode node) {
        if (node.children.isEmpty()) {
            node.generateChildren();
        }
        for (PokeTree.PokeNode child : node.children) {
            queue.add(child);
            if (child.aiHealth <= 0f) {
                lostStates++;
            }
            if (child.userHealth <= 0f) {
                finished = true;
                front = child;
                return;
            }
        }
    }
    public BFS(Battle battle) {
        start = System.currentTimeMillis();
        PokeTree pokeTree = new PokeTree(battle);
        if (pokeTree.root.userHealth <= 0f) {
            front = pokeTree.root;
            path.addFirst(front);
        } else {
            traverse(pokeTree.root);
            while (!finished && lostStates < 300000) {
                traverse(queue.remove());
                if (queue.isEmpty()) {
                    break;
                }
            }
            if (lostStates >= 300000) {
                this.stoppedToLost = true;
                path = null;
            }
            if (front != null && path != null) {
                path.addFirst(front);
                PokeTree.PokeNode parent = front.parent;
                while (parent != null) {
                    path.addFirst(parent);
                    parent = parent.parent;
                }
            }
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("BFS terminou em: " + timeElapsed/1000f + " segundos.");
    }

}
