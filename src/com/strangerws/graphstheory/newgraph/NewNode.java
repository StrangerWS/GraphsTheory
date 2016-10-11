package com.strangerws.graphstheory.newgraph;

import java.util.ArrayList;

/**
 * Created by DobryninAM on 11.10.2016.
 */
public class NewNode {
    String name;
    ArrayList<String> ins;
    ArrayList<String> outs;

    public NewNode(String name) {
        this.name = name;
    }

    public NewNode(ArrayList<String> ins, ArrayList<String> outs, String name) {
        this.ins = ins;
        this.outs = outs;
        this.name = name;
    }
}
