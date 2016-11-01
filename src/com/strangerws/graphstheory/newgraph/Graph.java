package com.strangerws.graphstheory.newgraph;

import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;


/**
 * Created by DobryninAM on 11.10.2016.
 */

public class Graph {
    private TreeSet<Node> graph;

    public Graph(String fileName) {
        graph = new TreeSet<Node>();
        try (BufferedReader fs = new BufferedReader(new FileReader(fileName))) {
            String line;
            Node tmp;
            Node search;

            while ((line = fs.readLine()) != null) {
                String[] mas = line.split(" ");

                if ((findNode(mas[0])) == null) {
                    tmp = new Node(mas[0]);
                    for (int i = 1; i < mas.length; i++) {
                        if (findNode(mas[i]) == null) {
                            search = new Node(mas[i]);
                            tmp.putNewOut(search);
                            search.putNewIn(tmp);
                        } else {
                            search = findNode(mas[i]);
                            tmp.putNewOut(search);
                            search.putNewIn(tmp);
                        }
                        graph.add(search);
                    }
                    graph.add(tmp);


                } else if (findNode(mas[0]) != null) {
                    tmp = findNode(mas[0]);
                    for (int i = 1; i < mas.length; i++) {
                        if ((findNode(mas[i])) == null) {
                            search = new Node(mas[i]);
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        } else {
                            search = findNode(mas[i]);
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        }
                        graph.add(search);
                    }
                    graph.add(tmp);
                }

            }

        } catch (IOException exc1) {
            System.out.println(exc1.getMessage());
        }

    }

    public Graph() {
        graph = new TreeSet<Node>();
    }

    public Graph(Graph anotherNewGraph) {
        graph = (TreeSet<Node>) anotherNewGraph.graph.clone();
    }

    @Nullable
    public Node findNode(String name) {
        for (Node node : graph) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void deleteNull() {
        for (int i = 0; i < graph.size(); ++i) {
            if (graph.iterator().next().ins.size() == 0 && graph.iterator().next().outs.size() == 0)
                graph.remove(graph.iterator().next());
        }
    }

    public void print() {
        for (Node node : graph) {
            node.print();
        }
    }
}
