package com.strangerws.graphstheory.model;

import com.strangerws.graphstheory.model.element.Edge;
import com.strangerws.graphstheory.model.element.Node;
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
                Edge edgeTemp;
                tmp = findNode(mas[0]);
                if (tmp == null) {
                    tmp = new Node(mas[0]);
                    for (int i = 1; i < mas.length; i++) {
                        if (findNode(mas[i]) == null) {
                            search = new Node(mas[i]);
                            edgeTemp = new Edge(tmp, search);
                            tmp.putNewOut(edgeTemp);
                            search.putNewIn(edgeTemp);
                        } else {
                            search = findNode(mas[i]);
                            edgeTemp = new Edge(tmp, search);
                            tmp.putNewOut(edgeTemp);
                            search.putNewIn(edgeTemp);
                        }
                        graph.add(search);
                    }
                    graph.add(tmp);


                } else if (tmp != null) {
                    for (int i = 1; i < mas.length; i++) {
                        if ((findNode(mas[i])) == null) {
                            search = new Node(mas[i]);
                            edgeTemp = new Edge(tmp, search);
                            search.putNewIn(edgeTemp);
                            tmp.putNewOut(edgeTemp);
                        } else {
                            search = findNode(mas[i]);
                            edgeTemp = new Edge(tmp, search);
                            search.putNewIn(edgeTemp);
                            tmp.putNewOut(edgeTemp);
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
        graph = new TreeSet<>();
        for (Node node : anotherGraph.graph) {
            this.addNode(node.getName(), node.getInsArray(), node.getOutsArray());
        }
    }

    public TreeSet<Node> getGraph() {
        return graph;
    }

    public void addNode(Node node) {
        graph.add(node);
        Edge edgeTemp;
        for (Edge in : node.getIns()) {
            Node tmp = findNode(in.getStart().getName());
            if (tmp != null) {
                edgeTemp = new Edge(tmp, node);
                tmp.putNewOut(edgeTemp);
            } else {
                tmp = new Node(in.getStart().getName());
                edgeTemp = new Edge(tmp, node);
                tmp.putNewOut(edgeTemp);
                graph.add(tmp);
            }
        }
        for (Edge out : node.getOuts()) {
            Node tmp = findNode(out.getEnd().getName());
            if (tmp != null) {
                edgeTemp = new Edge(node, tmp);
                tmp.putNewIn(edgeTemp);
            } else {
                tmp = new Node(out.getEnd().getName());
                edgeTemp = new Edge(node, tmp);
                tmp.putNewIn(edgeTemp);
                graph.add(tmp);
            }
        }
    }

    public void addNode(String nodeInfo, String[] ins, String[] outs) {
        Node node = findNode(nodeInfo);
        if (node == null) {
            node = new Node(nodeInfo);
        }

        Edge edgeTemp;

        for (String in : ins) {
            Node tmp = findNode(in);
            if (tmp != null) {
                edgeTemp = new Edge(tmp, node);
                node.putNewIn(edgeTemp);
                tmp.putNewOut(edgeTemp);
            } else {
                tmp = new Node(in);
                edgeTemp = new Edge(tmp, node);
                node.putNewIn(edgeTemp);
                tmp.putNewOut(edgeTemp);
                graph.add(tmp);
            }
        }

        for (String out : outs) {
            Node tmp = findNode(out);
            if (tmp != null) {
                edgeTemp = new Edge(node, tmp);
                node.putNewOut(edgeTemp);
                tmp.putNewIn(edgeTemp);
            } else {
                tmp = new Node(out);
                edgeTemp = new Edge(node, tmp);
                node.putNewOut(edgeTemp);
                tmp.putNewIn(edgeTemp);
                graph.add(tmp);
            }
        }

        graph.add(node);
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

    private void initializeSearch() {
        for (Node node : graph) {
            node.setUsed(false);
        }
    }

    public void depthFirstSearch(List<Node> nodes, Node node) {
        if (!node.isUsed()) {
            node.setUsed(true);
            nodes.add(node);
            for (Edge out : node.getOuts()) {
                if (!out.getEnd().isUsed()) {
                    depthFirstSearch(nodes, out.getEnd());
                    out.getEnd().setElderName(node.getName());
                }
            }
        }
    }

    private List<Node> breadthFirstSearch(Node node) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (!node.isUsed()) {
            queue.add(node);
            node.setUsed(true);
            while (!queue.isEmpty()) {
                Node tmp = queue.poll();
                nodes.add(tmp);
                for (Edge out : tmp.getOuts()) {
                    if (!out.getEnd().isUsed()) {
                        out.getEnd().setUsed(true);
                        out.getEnd().setElderName(tmp.getName());
                        queue.add(out.getEnd());
                    }
                }
            }
        }
        return nodes;
    }

    public List<Node> beginDFS(String nodeInfo) {
        initializeSearch();
        List<Node> nodes = new ArrayList<>();
        depthFirstSearch(nodes, findNode(nodeInfo));
        return nodes;
    }

    public List<Node> beginBFS(String nodeInfo) {
        initializeSearch();
        return breadthFirstSearch(findNode(nodeInfo));
    }

    private void dijkstraAlgorithm(String nodeInfo) {
        Node node = findNode(nodeInfo);
        for (Node value : graph) {
            value.setMinWeight(Integer.MIN_VALUE);
        }
        node.setMinWeight(0);
        for (Node value : graph) {
        }
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

    public List<Node> getNullNodes() {
        List<Node> nodes = new ArrayList<>();
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
    public List<Node> nodeGetInOut(String nodeInfo) {
        List<Node> nodes = new ArrayList<>();
        Node node = findNode(nodeInfo);
        for (Edge edge : node.getIns()) {
            if (node.getOuts().contains(new Edge(node, edge.getStart()))) {
                nodes.add(edge.getStart());
            }
        }
        return nodes;
    }

    //I-a-16
    public List<Node> nodesOnlyPath(String u, String v) {
        Node nodeU = findNode(u);
        Node nodeV = findNode(v);
        List<Node> nodes = new ArrayList<>();
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

    //II-2
    public List<Node> getUnaccessibleNodesFromNode(String nodeInfo) {
        List<Node> nodes = new ArrayList<>();
        List<Node> bfs = beginBFS(nodeInfo);

        for (Node node : graph) {
            if (!bfs.contains(node)) {
                nodes.add(node);
            }
        }

        return nodes;
    }

    //II-24
    public int getArkCountFromMinimalPath(String nodeInfoU, String nodeInfoV) {
        beginBFS(nodeInfoU);
        int count = 1;

        Node tmp = findNode(nodeInfoV);
        if (findNode(nodeInfoU) != null || tmp != null) {
            if (tmp.getElderName() == null) {
                return 0;
            }
            while (!nodeInfoU.equals(tmp.getElderName())) {
                tmp = findNode(tmp.getElderName());
                count++;
            }
        }

        return count;
    }

    //II-30
    public Map<Node, Integer> getMinimalLengthFromAllNodesToNode(String nodeInfo) {
        Map<Node, Integer> lengths = new LinkedHashMap<>();

        Node tmp = findNode(nodeInfo);

        for (Node node : graph) {
            if (!node.getName().equals(tmp.getName())) {
                lengths.put(node, getArkCountFromMinimalPath(tmp.getName(), node.getName()));
            }
        }

        return lengths;
    }

    //TODO - III - Остовное дерево по Краскалу
    public void getKruskalTree() {
    }

    //TODO - IV-a-5
    public List<Integer> getMinimalLengthFromNodeToAllNodes(String nodeInfo) {
        List<Integer> lengths = new ArrayList<>();
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
    public List<Node> getNegativeWeightCycle() {
        List<Node> nodes = new ArrayList<>();
        //TODO
        return nodes;
    }
}
