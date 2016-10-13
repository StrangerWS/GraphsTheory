package com.strangerws.graphstheory.newgraph;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by DobryninAM on 11.10.2016.
 */
public class NewNode {
    String name;
    TreeSet<NewEdge> ins;
    TreeSet<NewEdge> outs;

    public NewNode(String name) {
        this.name = name;
    }

    public NewNode(TreeSet<NewEdge> ins, TreeSet<NewEdge> outs, String name) {
        this.ins = ins;
        this.outs = outs;
        this.name = name;
    }
}