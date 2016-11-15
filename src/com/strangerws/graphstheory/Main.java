package com.strangerws.graphstheory;

import com.strangerws.graphstheory.model.newgraph.Graph;
import com.strangerws.graphstheory.model.newgraph.element.Node;
import com.strangerws.graphstheory.view.impl.GraphView;

/**
 * Created by DobryninAM on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph1 = new Graph("resources\\test.txt");
        Graph graph1copy = new Graph(graph1);
        GraphView view = new GraphView();

        view.printGraph(graph1, "Original");
        view.printGraph(graph1copy, "Copy");
        graph1.deleteNull();
        view.printGraph(graph1, "Original");
        view.printGraph(graph1copy, "Copy");
        view.printAdjacentList(graph1);
        graph1.addNode(new Node(String.valueOf(8)), new String[]{String.valueOf(2), String.valueOf(9)}, new String[]{String.valueOf(6), String.valueOf(9)});
        view.printGraph(graph1);

        view.printNodes(graph1.nodesOnlyPath(String.valueOf(1), String.valueOf(2)), "Nodes only path for 1 and 2:");
        view.printNodes(graph1.nodeGetInOut(String.valueOf(1)), "In and Out nodes for node 1:");
    }
}