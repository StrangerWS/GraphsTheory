package com.strangerws.graphstheory;

import com.strangerws.graphstheory.model.Graph;
import com.strangerws.graphstheory.view.GraphView;

/**
 * Created by DobryninAM on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph1 = new Graph("resources\\test.txt");
        Graph graph2 = new Graph("resources\\test2.txt");
        Graph graph3 = new Graph("resources\\test3.txt");
        Graph graph4 = new Graph("resources\\test4.txt");
        Graph graphCopy = new Graph(graph1);
        GraphView view = new GraphView();

        view.printGraph(graph1, "Original");
        view.printGraph(graphCopy, "Copy");
        view.printAdjacentList(graph1);

        graph1.addNode(String.valueOf(8), new String[]{String.valueOf(2), String.valueOf(9)});
        view.printGraph(graph1, "Original - added 8");
        view.printGraph(graphCopy, "Copy - added 8");

        view.printNodes(graph1.getNullNodes(), "Null nodes:");
        graph1.deleteNullNodes();
        view.printGraph(graph1, "Original - deleted nulls");
        view.printGraph(graphCopy, "Copy - deleted nulls");
        view.printNodes(graph1.nodesOnlyPath(String.valueOf(1), String.valueOf(2)), "Nodes only path for 1 and 2:");
        view.printNodes(graph1.nodeGetInOut(String.valueOf(1)), "In and Out nodes for node 1:");

        graph1.merge(graph2);
        view.printAdjacentList(graph1, "Merged graphs:");
        view.printGraph(graphCopy);

        view.printNodes(graphCopy.beginBFS(String.valueOf(1)), "BFS:");
        view.printNodes(graphCopy.beginDFS(String.valueOf(1)), "DFS:");

        view.printNodes(graphCopy.getUnaccessibleNodesFromNode(String.valueOf(1)), "Unaccessible from 1:");
        view.printInteger(graphCopy.getArkCountFromMinimalPath(String.valueOf(1), String.valueOf(2)), "Ark Count for nodes 1 and 2:");
        view.printMap(graphCopy.getMinimalLengthFromAllNodesToNode(String.valueOf(1)), "Minimal Path from all nodes to 1:");

        view.printGraph(graphCopy.getKruskalTree());

        view.printIntegers(graphCopy.getMinimalLengthFromNodeToAllNodesByFloyd(String.valueOf(2)), "Minimal Path from 2 to all nodes (Floyd):");
        view.printIntegers(graphCopy.getMinimalLengthFromNodeToAllNodesByDijkstra(String.valueOf(2)), "Minimal Path from 2 to all nodes (Dijkstra):");
        view.printInteger(graphCopy.getMinimalLength(String.valueOf(1), String.valueOf(6)), "Minimal Path from 1 to 6:");
        view.printNodes(graphCopy.getNegativeWeightCycle(), "Negative weight cycle:");

        view.printInteger(graph4.getMaxFlow("s", "t"), "Max Flow from s to t:");
    }
}