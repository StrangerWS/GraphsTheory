package com.strangerws.graphstheory.view.impl;

import com.strangerws.graphstheory.model.newgraph.element.Node;
import com.strangerws.graphstheory.view.api.View;

import java.util.ArrayList;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GraphView implements View {

    @Override
    public void print(ArrayList<Node> nodes, String what) {
        System.out.println(what);
        for (Node node : nodes) {
            System.out.println(node.getName());
        }
    }
}
