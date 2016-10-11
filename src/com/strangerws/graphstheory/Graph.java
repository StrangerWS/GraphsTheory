package com.strangerws.graphstheory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DobryninAM on 27.09.2016.
 */
public class Graph {
    private class Node {
        String info;
        ArrayList<String> adjacent;

        Node(String nodeName, ArrayList<String> adjacentList) {
            info = nodeName;
            adjacent = adjacentList;
        }

        int getInDegree() {
            return adjacent.size();
        }

        void print() {
            System.out.print(info + " ");
            for (Object anAdjacent : adjacent) {
                System.out.print(anAdjacent + " ");
            }
            System.out.println();
        }
    }

    ArrayList<Node> graph;
    boolean isOriented = false;

    public Graph(String name) {
        try (BufferedReader fs = new BufferedReader(new FileReader(name))) {
            String line;
            graph = new ArrayList<>();
            while ((line = fs.readLine()) != null) {
                ArrayList<String> tmp = new ArrayList<>();
                String[] mas = line.split(" ");
                tmp.addAll(Arrays.asList(mas).subList(1, mas.length));
                graph.add(new Node(mas[0], tmp));
            }
        } catch (IOException exc1) {
            System.out.println("Error in reading file!");
        }
    }

    public Graph(String name, String orientation) {
        try (BufferedReader fs = new BufferedReader(new FileReader(name))) {
            String line;
            graph = new ArrayList<>();
            while ((line = fs.readLine()) != null) {
                ArrayList<String> tmp = new ArrayList<>();
                String[] mas = line.split(" ");
                tmp.addAll(Arrays.asList(mas).subList(1, mas.length));
                graph.add(new Node(mas[0], tmp));
                if (orientation.equals("oriented")) {
                    isOriented = true;
                }
            }
        } catch (IOException exc1) {
            System.out.println("Error in reading file!");
        }
    }

    public Graph() {
        graph = new ArrayList<Node>(0);
    }

    public Graph(Graph anotherGraph) {
        graph = (ArrayList<Node>) anotherGraph.graph.clone();
    }

    public void addNode(String nodeInfo, ArrayList<String> adjacentList) {
        graph.add(new Node(nodeInfo, adjacentList));
    }

    public void addNode(Node node) {
        graph.add(node);
    }

    public void deleteNode(String nodeInfo) {
    }

    public void print() {
        if (!graph.isEmpty()) {
            for (Node aGraph : graph) {
                aGraph.print();
            }
            System.out.println();
        } else System.out.println("Graph is empty\n");
    }

    public void deleteNull() {
        for (int i = 0; i < graph.size(); ++i)
            if (graph.get(i).getInDegree() + countOutDegree(graph.get(i)) == 0)
                graph.remove(graph.get(i));
    }

    private int countOutDegree(Node node) {
        int cnt = 0;
        for (Node aGraph : graph) {
            if (node.info != aGraph.info) {
                if (aGraph.adjacent.contains(node.info)) cnt++;
            }
        }
        return cnt;
    }

    public ArrayList<String> nodeInOut(String nodeInfo) {
        ArrayList<String> arrayInOut = new ArrayList<>();
        try {
            Node node = nodeFind(nodeInfo);

            for (String str : node.adjacent) {
                Node tmp = nodeFind(str);
                if (tmp.adjacent.contains(nodeInfo)) {
                    arrayInOut.add(tmp.info);
                }
            }
        } catch (NullPointerException exc1) {
            System.out.println("Node not found!");
        }
        return arrayInOut;
    }

    private Node nodeFind(String nodeInfo) {
        for (Node node : graph)
            if (node.info.equals(nodeInfo))
                return node;
        return null;
    }

    public ArrayList<String> nodesOnlyPath(String u, String v) {
        ArrayList<String> arrayOnlyPath = new ArrayList<>();

        for (Node node : graph) {
            if (node.adjacent.contains(u) && node.adjacent.contains(v))
                arrayOnlyPath.add(node.info);
        }

        return arrayOnlyPath;
    }

    public static Graph MergeOrientedGraphs(Graph graph1, Graph graph2) {
        Graph newGraph = new Graph();
        if (graph1.isOriented || graph2.isOriented) {
            newGraph = new Graph(graph1);

            for (Node node : newGraph.graph) {
                try {
                    if (node.info.equals(graph2.nodeFind(node.info).info)) {
                        for (String str : graph2.nodeFind(node.info).adjacent) {
                            if (!node.adjacent.contains(str)) {
                                node.adjacent.add(str);
                            }
                        }
                    }
                    else {
                        newGraph.addNode(node);
                    }
                } catch (NullPointerException exc) {
                    System.out.println("Cannot find node!");
                }
            }
        }
        return newGraph;
    }
}