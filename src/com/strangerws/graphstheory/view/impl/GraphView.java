package com.strangerws.graphstheory.view.impl;

import com.strangerws.graphstheory.model.Graph;
import com.strangerws.graphstheory.model.element.Edge;
import com.strangerws.graphstheory.model.element.Node;
import com.strangerws.graphstheory.view.api.View;

import java.util.ArrayList;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GraphView implements View {

    @Override
    public void printGraph(Graph graph) {
        for (Node node : graph.getGraph()) {
            System.out.print(node.getName());
            System.out.print(" Ins:");

            if (!node.getIns().isEmpty()) {
                for (Edge in : node.getIns()) {
                    System.out.print(" " + in.getStart().getName());
                }
            } else {
                System.out.print(" None");
            }

            System.out.print(" Outs:");

            if (!node.getOuts().isEmpty()) {
                for (Edge out : node.getOuts()) {
                    System.out.print(" " + out.getEnd().getName());
                }
            } else {
                System.out.print(" None");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printGraph(Graph graph, String name) {
        System.out.println(name);
        printGraph(graph);
    }

    @Override
    public void printNodes(ArrayList<Node> nodes, String what) {
        System.out.print(what);
        for (Node node : nodes) {
            System.out.print(String.format(" %s", node.getName()));
        }
        System.out.println();
        System.out.println();
    }

    public void printAdjacentList(Graph graph) {
        for (Node node : graph.getGraph()) {
            System.out.print(node.getName());
            for (Edge out : node.getIns()) {
                System.out.print(" " + out.getStart().getName());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printAdjacentList(Graph graph, String info) {
        System.out.println(info);
        printAdjacentList(graph);
    }
}
