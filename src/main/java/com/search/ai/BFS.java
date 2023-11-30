package com.search.ai;

import com.search.pokejava.Battle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private final Queue<PokeTree.PokeNode> queue = new LinkedList<>();
    private PokeTree.PokeNode front;
    public ArrayList<PokeTree.PokeNode> path = new ArrayList<>();
    public int iterations = 0;
    public long start, finish, timeElapsed;
    private boolean finished = false;

    private void traverse(PokeTree.PokeNode node) {
        if (node.children.isEmpty()) {
            node.generateChildren();
        }
        for (PokeTree.PokeNode child : node.children) {
            iterations++;
            queue.add(child);
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
            while (!finished) {
                traverse(queue.remove());
                if (queue.isEmpty()) {
                    break;
                }
            }
            if (front != null) {
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
        System.out.println("BFS terminou em: " + timeElapsed/60f + " segundos.");
    }

}
