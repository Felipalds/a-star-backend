package com.search;

public class Pair {

    int first;
    int second;
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pair && this.first == ((Pair)obj).first && this.second == ((Pair)obj).second;
    }
}
