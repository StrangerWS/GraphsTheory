package com.strangerws.graphstheory.view.api;

import com.strangerws.graphstheory.model.Graph;
import com.strangerws.graphstheory.model.element.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface View {

    void printGraph(Graph graph);

    void printGraph(Graph graph, String name);

    void printNodes(List<Node> nodes, String what);

    void printAdjacentList(Graph graph);

    void printAdjacentList(Graph graph, String info);

    void printInteger(int integer, String info);

    void printMap(Map<Node, Integer> paths, String info);
}
