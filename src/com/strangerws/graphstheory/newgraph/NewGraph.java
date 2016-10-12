/*
package com.strangerws.graphstheory.newgraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

*/
/**
 * Created by DobryninAM on 11.10.2016.
 *//*

public class NewGraph {
    private ArrayList<NewNode> graph;

    public NewGraph(String fileName) {
        graph = new ArrayList<>();
        try (BufferedReader fs = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = fs.readLine()) != null) {
                String[] mas = line.split(" ");
                for (int i = 1; i <= mas.length; ++i){
                    if (graph.contains(new NewNode(mas[i])))
                }
            }
        } catch (IOException exc1) {
            System.out.println(exc1.getMessage());
        }
    }

    public NewGraph() {
        graph = new ArrayList<>(0);
    }

    public NewGraph(NewGraph anotherNewGraph) {
        graph = (ArrayList<NewNode>) anotherNewGraph.graph.clone();
    }
}
*/
