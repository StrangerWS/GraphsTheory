package com.strangerws.graphstheory;

import com.strangerws.graphstheory.model.newgraph.Graph;
import com.strangerws.graphstheory.view.impl.GraphView;

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

       // new GraphView().print(graph1.nodesOnlyPath(String.valueOf(1), String.valueOf(3), new String("Nodes 1-3 only path: ")));
    }
}