package com.strangerws.graphstheory.view.impl;

import com.strangerws.graphstheory.model.Graph;
import com.strangerws.graphstheory.model.element.Edge;
import com.strangerws.graphstheory.model.element.Node;
import com.strangerws.graphstheory.view.api.View;

import java.util.List;
import java.util.Map;

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

    @Override
    public void printGraph(Graph graph, String name) {
        System.out.println(name);
        printGraph(graph);
    }

    @Override
    public void printNodes(List<Node> nodes, String info) {
        System.out.print(info);
        if (nodes.size() == 0) {
            System.out.println(" None");
        } else {
            for (Node node : nodes) {
                System.out.print(String.format(" %s", node.getName()));
            }
        }
        System.out.println();
    }

    @Override
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

    @Override
    public void printAdjacentList(Graph graph, String info) {
        System.out.println(info);
        printAdjacentList(graph);
    }

    @Override
    public void printInteger(int integer, String info) {
        System.out.print(info);
        System.out.println(String.format(" %s", integer));
        System.out.println();
    }

    @Override
    public void printMap(Map<Node, Integer> paths, String info) {
        System.out.println(info);
        for (Map.Entry<Node, Integer> entry : paths.entrySet()) {
            System.out.print(String.format("%s ", entry.getKey().getName()));
        }
        System.out.println();
        for (Map.Entry<Node, Integer> entry : paths.entrySet()) {
            System.out.print(String.format("%s ", entry.getValue()));
        }
        System.out.println();
        System.out.println();
    }

    public void printIntegers(List<Integer> integers) {
        if (integers.size() == 0) {
            System.out.println(" None");
        } else {
            for (Integer integer : integers) {
                System.out.print(String.format(" %s", integer));
            }
        }
        System.out.println();
        System.out.println();
    }

    public void printIntegers(List<Integer> integers, String info) {
        System.out.print(info);
        if (integers.size() == 0) {
            System.out.println(" None");
        } else {
            for (Integer integer : integers) {
                System.out.print(String.format(" %s", integer));
            }
        }
        System.out.println();
        System.out.println();
    }
}
