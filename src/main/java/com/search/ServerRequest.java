package com.search;

public class ServerRequest {

    AlgorithmEnum algorithm;
    int[][] matrix;
    int[] start;
    int[] end;

    public int [][] getMatrix() {
        return matrix;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }

    public AlgorithmEnum getAlgorithm () {
        return algorithm;
    }
}