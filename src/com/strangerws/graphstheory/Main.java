package com.strangerws.graphstheory;

import com.strangerws.graphstheory.newgraph.NewGraph;

import java.util.ArrayList;

/**
 * Created by DobryninAM on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        String node = "1";

        NewGraph graph1 = new NewGraph("C:\\Users\\DobryninAM\\Documents\\TextFiles\\test.txt");
        NewGraph graphNull = new NewGraph();
        NewGraph graph1copy = new NewGraph(graph1);

        graph1.print();
        graphNull.print();
        graph1copy.print();

        graph1copy.deleteNull();
        System.out.println("Original");
        graph1.print();
        System.out.println("Copy");
        graph1copy.print();
        ArrayList<String> printed = graph1.nodeInOut(node);

        System.out.println("For node " + node + " in-out nodes are: ");
        for (String str : printed) {
            System.out.print(str + " ");
        }
        System.out.println();

        printed = graph1.nodesOnlyPath("1", "2");

        System.out.println("For nodes 1 and 2 only path nodes are: ");
        for (String str : printed) {
            System.out.print(str + " ");
        }
        System.out.println();

        graph1.deleteNode("6");
        graph1.print();
    }
}