package com.strangerws.graphstheory.model.newgraph;

import com.strangerws.graphstheory.model.newgraph.element.Edge;
import com.strangerws.graphstheory.model.newgraph.element.Node;
import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by DobryninAM on 11.10.2016.
 */

public class Graph {
    private TreeSet<Node> graph;

    public Graph(String fileName) {
        graph = new TreeSet<>();
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
        graph = new TreeSet<>();
    }

    public Graph(Graph anotherGraph) {
        graph = (TreeSet<Node>) anotherGraph.graph.clone();
    }

    @Nullable
    public Node findNode(String name) {
        for (Node node : graph) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void deleteNull() {
        try {
            for (Node node : graph) {
                if (node.getIns().size() == 0 && node.getOuts().size() == 0){
                    graph.remove(node);
                }
            }
        } catch (ConcurrentModificationException e){
            //TODO - обработка
            e.getStackTrace();
        }
    }

    public void print() {
        for (Node node : graph) {
            node.print();
        }
    }

    public ArrayList<Node> nodesOnlyPath(String u, String v){
        //TODO - оптимизация
        Node nodeU = findNode(u);
        Node nodeV = findNode(v);
        ArrayList<Node> nop = new ArrayList<>();
        for (Edge edge : nodeU.getOuts()){
            Node tmp = edge.getEnd();
            for (Edge edge1 : tmp.getOuts()){
                for (Edge edge2 : tmp.getIns()){
                    if (edge1.getEnd().equals(edge2.getStart()) && edge1.getStart().equals(nodeU)){
                        nop.add(edge1.getEnd());
                    }
                }
            }
        }
        return nop;
    }
}
