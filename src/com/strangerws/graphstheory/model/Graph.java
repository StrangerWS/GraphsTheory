package com.strangerws.graphstheory.model;

import com.strangerws.graphstheory.model.element.Edge;
import com.strangerws.graphstheory.model.element.Node;
import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.TreeSet;


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

    public TreeSet<Node> getGraph() {
        return graph;
    }

    public void addNode(Node node) {
        graph.add(node);

        for (Edge in : node.getIns()) {
            Node tmp = findNode(in.getStart().getName());
            if (tmp != null) {
                node.putNewIn(tmp);
                tmp.putNewOut(node);
            } else {
                tmp = new Node(in.getStart().getName());
                node.putNewIn(tmp);
                tmp.putNewOut(node);
                graph.add(tmp);
            }
        }
        for (Edge out : node.getOuts()) {
            Node tmp = findNode(out.getEnd().getName());
            if (tmp != null) {
                node.putNewOut(tmp);
                tmp.putNewIn(node);
            } else {
                tmp = new Node(out.getEnd().getName());
                node.putNewOut(tmp);
                tmp.putNewIn(node);
                graph.add(tmp);
            }
        }
    }

    public void addNode(String nodeInfo, String[] ins, String[] outs) {
        Node node = new Node(nodeInfo);
        graph.add(node);

        for (String in : ins) {
            if (findNode(in) != null) {
                node.putNewIn(findNode(in));
                findNode(in).putNewOut(node);
            } else {
                Node tmp = new Node(in);
                node.putNewIn(tmp);
                tmp.putNewOut(node);
                graph.add(tmp);
            }
        }
        for (String out : outs) {
            Node tmp = findNode(out);
            if (tmp != null) {
                node.putNewOut(tmp);
                tmp.putNewIn(node);
            } else {
                tmp = new Node(out);
                node.putNewOut(tmp);
                tmp.putNewIn(node);
                graph.add(tmp);
            }
        }
    }

    public void removeNode(Node node) {
        if (graph.contains(node)) {
            graph.remove(node);
        }
    }

    public void removeNode(String nodeInfo) {
        graph.remove(findNode(nodeInfo));
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

    private void depthFirstSearch() {
        //TODO
    }

    private void breadthFirstSearch() {
        //TODO
    }

    private void dijkstraAlgorithm() {
        //TODO
    }

    private void fordBellmanAlgorithm() {
        //TODO
    }

    private void floydAlgorithm() {
        //TODO
    }

    //I-a-6
    public void deleteNullNodes() {
        for (Node node : getNullNodes()) {
            graph.remove(node);
        }
    }

    public ArrayList<Node> getNullNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        try {
            for (Node node : graph) {
                if (node.getIns().size() == 0 && node.getOuts().size() == 0) {
                    nodes.add(node);
                }
            }
        } catch (ConcurrentModificationException e) {
            //TODO - обработка
            e.getStackTrace();
        }
        return nodes;
    }

    //I-a-12
    public ArrayList<Node> nodeGetInOut(String nodeInfo) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node node = findNode(nodeInfo);
        for (Edge edge : node.getIns()) {
            if (node.getOuts().contains(new Edge(node, edge.getStart()))) {
                nodes.add(edge.getStart());
            }
        }
        return nodes;
    }

    //I-a-16
    public ArrayList<Node> nodesOnlyPath(String u, String v) {
        Node nodeU = findNode(u);
        Node nodeV = findNode(v);
        ArrayList<Node> nodes = new ArrayList<>();
        for (Edge edge : nodeU.getOuts()) {
            Node tmp = edge.getEnd();
            if (tmp.getIns().contains(new Edge(nodeU, tmp)) && tmp.getOuts().contains(new Edge(tmp, nodeV))) {
                nodes.add(tmp);
            }
        }
        return nodes;
    }

    //I-b-5
    public void merge(Graph anotherGraph) {
        for (Node node : anotherGraph.getGraph()) {
            this.addNode(node);
        }
    }

    //TODO - II-2
    public ArrayList<Node> getUnaccessibleNodesFromNode(Node node) {
        ArrayList<Node> nodes = new ArrayList<>();
        //TODO

        return nodes;
    }

    //TODO - II-24
    public int getArkContFromMinimalPath() {
        int count = 0;
        //TODO
        return count;
    }

    //TODO - II-30
    public ArrayList<Integer> getMinimalLengthFromAllNodesToNode(Node node) {
        ArrayList<Integer> lengths = new ArrayList<>();
        //TODO
        return lengths;
    }

    //TODO - III - Остовное дерево по Краскалу
    public void getKruskalTree() {
        //TODO
    }

    //TODO - IV-a-5
    public ArrayList<Integer> getMinimalLengthFromNodeToAllNodes(Node node) {
        ArrayList<Integer> lengths = new ArrayList<>();
        //TODO

        return lengths;
    }

    //TODO - IV-b-12
    public int getMinimalLength(String u, String v) {
        int length = 0;
        //TODO
        return length;
    }

    //TODO - IV-c-18
    public ArrayList<Node> get
}
