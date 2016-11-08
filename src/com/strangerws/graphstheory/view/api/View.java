package com.strangerws.graphstheory.view.api;

import com.strangerws.graphstheory.model.newgraph.element.Node;

import java.util.ArrayList;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface View {

    void print(ArrayList<Node> nodes, String what);
}
