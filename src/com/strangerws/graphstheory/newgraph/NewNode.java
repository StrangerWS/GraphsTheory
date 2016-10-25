package com.strangerws.graphstheory.newgraph;

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by DobryninAM on 11.10.2016.
 */
public class NewNode {
    String name;
    TreeSet<NewEdge> ins;
    TreeSet<NewEdge> outs;

    public NewNode(){
        ins = new TreeSet<>();
        outs = new TreeSet<>();
    }
    public NewNode(String name) {
        this.name = name;
        ins = new TreeSet<>();
        outs = new TreeSet<>();
    }
    public NewNode(String name, TreeSet<NewEdge> ins, TreeSet<NewEdge> outs) {
        this.name = name;
        this.ins = ins;
        this.outs = outs;
    }

    public void putNewOut(NewNode end) {
        ins.add(new NewEdge(this, end));
    }
    public void putNewIn(NewNode start){
        outs.add(new NewEdge(start, this));
    }
}