package com.strangerws.graphstheory.model.newgraph.element;

import java.util.TreeSet;

/**
 * Created by DobryninAM on 11.10.2016.
 */
public class Node implements Comparable<Node> {
    private String name;
    private TreeSet<Edge> ins;
    private TreeSet<Edge> outs;

    public Node() {
        ins = new TreeSet<>();
        outs = new TreeSet<>();
    }

    public Node(String name) {
        this.name = name;
        ins = new TreeSet<>();
        outs = new TreeSet<>();
    }

    public Node(String name, TreeSet<Edge> ins, TreeSet<Edge> outs) {
        this.name = name;
        this.ins = ins;
        this.outs = outs;
    }

    public void putNewOut(Node end) {
        outs.add(new Edge(this, end));
    }

    public void putNewIn(Node start) {
        ins.add(new Edge(start, this));
    }


    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public TreeSet<Edge> getIns() {
        return ins;
    }

    public TreeSet<Edge> getOuts() {
        return outs;
    }
}