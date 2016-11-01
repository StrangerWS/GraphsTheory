package com.strangerws.graphstheory;

import com.strangerws.graphstheory.newgraph.Graph;

/**
 * Created by DobryninAM on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph1 = new Graph("C:\\Users\\DobryninAM\\Documents\\TextFiles\\test.txt");
        Graph graphNull = new Graph();
        Graph graph1copy = new Graph(graph1);

        graph1.print();
        graphNull.print();
        graph1copy.print();

        graph1copy.deleteNull();
        System.out.println("Original");
        graph1.print();
        System.out.println("Copy");
        graph1copy.print();
    }
}