package com.strangerws.graphstheory.model.element;

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
        int len1 = name.length();
        int len2 = o.name.length();

        if (len1 != len2) {
            return len1 - len2;
        } else {
            int lim = Math.min(len1, len2);
            char v1[] = name.toCharArray();
            char v2[] = o.name.toCharArray();

            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c1 - c2;
                }
                k++;
            }
        }
        return 0;
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