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
                String[] element = new String[]{"", "0"};
                if (tmp == null) {
                    tmp = new Node(mas[0]);
                    for (int i = 1; i < mas.length; i++) {
                        if (mas[i].contains("-")) {
                            element = mas[i].split("-");
                        } else {
                            element[0] = mas[i];
                        }
                        if (findNode(element[0]) == null) {
                            search = new Node(element[0]);
                            edgeTemp = new Edge(tmp, search, Integer.parseInt(element[1]));
                            tmp.putNewOut(edgeTemp);
                            search.putNewIn(edgeTemp);
                        } else {
                            search = findNode(element[0]);
                            edgeTemp = new Edge(tmp, search, Integer.parseInt(element[1]));
                            tmp.putNewOut(edgeTemp);
                            search.putNewIn(edgeTemp);
                        }
                        graph.add(search);
                    }
                    graph.add(tmp);


                } else if (tmp != null) {
                    for (int i = 1; i < mas.length; i++) {
                        if (mas[i].contains("-")) {
                            element = mas[i].split("-");
                        } else {
                            element[0] = mas[i];
                        }
                        if ((findNode(element[0])) == null) {
                            search = new Node(element[0]);
                            edgeTemp = new Edge(tmp, search, Integer.parseInt(element[1]));
                            search.putNewIn(edgeTemp);
                            tmp.putNewOut(edgeTemp);
                        } else {
                            search = findNode(element[0]);
                            edgeTemp = new Edge(tmp, search, Integer.parseInt(element[1]));
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
            this.addNode(node.getName(), node.getOutsArray());
        }
    }

    public TreeSet<Node> getGraph() {
        return graph;
    }

    public Graph buildFromMatrix(int[][] matrix) {
        //TODO
        Graph graph = new Graph();
        Node node1;
        Node node2;
        Edge edge;

        for (int i = 0; i < matrix.length; i++) {
            graph.graph.add(new Node(String.valueOf(i + 1)));
        }

        for (int i = 0; i < matrix.length; i++) {
            node1 = findNode(String.valueOf(i + 1));
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    node2 = findNode(String.valueOf(j + 1));
                    edge = new Edge(node1, node2, matrix[i][j]);
                    node1.putNewOut(edge);
                    node2.putNewIn(edge);
                }
            }
        }
        return graph;
    }

    public void addNode(Node node) {
        //TODO - Fix
        if (findNode(node.getName()) == null) {
            graph.add(node);
        }
        Edge edgeTemp;
        for (Edge in : node.getIns()) {
            Node tmp = findNode(in.getStart().getName());
            if (tmp != null) {
                edgeTemp = new Edge(tmp, node, in.getWeight());
                tmp.putNewOut(edgeTemp);
            } else {
                tmp = new Node(in.getStart().getName());
                edgeTemp = new Edge(tmp, node, in.getWeight());
                tmp.putNewOut(edgeTemp);
                graph.add(tmp);
            }
        }
        for (Edge out : node.getOuts()) {
            Node tmp = findNode(out.getEnd().getName());
            if (tmp != null) {
                edgeTemp = new Edge(node, tmp, out.getWeight());
                tmp.putNewIn(edgeTemp);
            } else {
                tmp = new Node(out.getEnd().getName());
                edgeTemp = new Edge(node, tmp, out.getWeight());
                tmp.putNewIn(edgeTemp);
                graph.add(tmp);
            }
        }
    }

    public void addNode(String nodeInfo, String[] outs) {
        Node node = findNode(nodeInfo);
        if (node == null) {
            node = new Node(nodeInfo);
        }
        if (outs != null) {
            Edge edgeTemp;
            String[] element = new String[]{"", "0"};

            for (String out : outs) {
                if (out.contains("-")) {
                    element = out.split("-");
                } else {
                    element[0] = out;
                }
                Node tmp = findNode(element[0]);
                if (tmp != null) {
                    edgeTemp = new Edge(node, tmp, Integer.parseInt(element[1]));
                    node.putNewOut(edgeTemp);
                    tmp.putNewIn(edgeTemp);
                } else {
                    tmp = new Node(element[0]);
                    edgeTemp = new Edge(node, tmp, Integer.parseInt(element[1]));
                    node.putNewOut(edgeTemp);
                    tmp.putNewIn(edgeTemp);
                    graph.add(tmp);
                }
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

    }

    private int[][] floydAlgorithm() {
        int[][] matrix = getWeightMatrixForGraph();
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        return matrix;
    }

    private int[][] getWeightMatrixForGraph() {
        int[][] matrix = new int[graph.size()][graph.size()];
        List<Node> graphList = new ArrayList<>(graph);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            graphList.get(i).setId(i);
            for (Edge edge : graphList.get(i).getOuts()) {
                matrix[i][graphList.indexOf(edge.getEnd())] = edge.getWeight();
            }
        }

        return matrix;
    }

    private Set<Edge> getUnorientedGraphEdges() {
        Set<Edge> edges = new TreeSet<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getEnd() == o2.getStart() && o1.getStart() == o2.getEnd() && o1.getWeight() == o2.getWeight()) {
                    return 0;
                } else return o1.compareTo(o2);
            }
        });

        for (Node n : graph) {
            for (Edge e : n.getOuts()) {
                edges.add(e);
            }
        }

        return edges;
    }

    //I-a-6
    public void deleteNullNodes() {
        for (Node node : getNullNodes()) {
            graph.remove(node);
        }
    }

    public List<Node> getNullNodes() {
        List<Node> nodes = new ArrayList<>();
        for (Node node : graph) {
            if (node.getIns().size() == 0 && node.getOuts().size() == 0) {
                nodes.add(node);
            }
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

    //III - Остовное дерево по Краскалу
    public Graph getKruskalTree() {
        List<Edge> edges = new ArrayList<>(getUnorientedGraphEdges());
        Graph tree = new Graph();
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getWeight() > o2.getWeight()) {
                    return 1;
                } else if (o1.getWeight() < o2.getWeight()) {
                    return -1;
                } else return 0;
            }
        });

        for (Node node : graph) {
            tree.addNode(node.getName(), null);
        }

        tree.initializeSearch();

        Node edgeStart;
        Node edgeEnd;
        Edge edgeTemp;
        Edge edgeTempRev;

        for (Edge e : edges) {
            edgeStart = tree.findNode(e.getStart().getName());
            edgeEnd = tree.findNode(e.getEnd().getName());

            if (!edgeEnd.isUsed()) {
                edgeTemp = new Edge(edgeStart, edgeEnd, e.getWeight());
                edgeTempRev = new Edge(edgeEnd, edgeStart, e.getWeight());
                edgeStart.putNewOut(edgeTemp);
                edgeStart.putNewIn(edgeTempRev);
                edgeEnd.putNewIn(edgeTemp);
                edgeEnd.putNewOut(edgeTempRev);
                edgeEnd.setUsed(true);
            }
        }

        tree.initializeSearch();

        return tree;
    }

    //IV-b-5
    public List<Integer> getMinimalLengthFromNodeToAllNodes(String nodeInfo) {
        List<Integer> lengths = new ArrayList<>();

        Node node = findNode(nodeInfo);

        int[][] weightMatrix = floydAlgorithm();
        for (int i : weightMatrix[node.getId()]) {
            lengths.add(i);
        }
        return lengths;
    }

    //IV-b-12
    public int getMinimalLength(String u, String v) {
        int length = 0;

        Node nodeU = findNode(u);
        Node nodeV = findNode(v);

        int[][] weightMatrix = floydAlgorithm();

        return weightMatrix[nodeU.getId()][nodeV.getId()];
    }

    //TODO - IV-c-18
    public List<Node> getNegativeWeightCycle() {
        List<Node> nodes = new ArrayList<>();
        //TODO
        return nodes;
    }
}