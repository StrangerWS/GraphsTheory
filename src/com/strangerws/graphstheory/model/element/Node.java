package com.strangerws.graphstheory.model.element;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by DobryninAM on 11.10.2016.
 */
public class Node implements Comparable<Node>, Cloneable {
    private String name;
    private TreeSet<Edge> ins;
    private TreeSet<Edge> outs;
    private boolean isUsed = false;
    private String elderName;
    private int minWeight;

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

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

    public Node(Node anotherNode) {
        this.name = anotherNode.name;
        ins = new TreeSet<>();
        outs = new TreeSet<>();
        for (Edge edge : ins) {
            this.ins.add(new Edge(edge));
        }
        for (Edge edge : outs) {
            this.outs.add(new Edge(edge));
        }
    }

    public String[] getInsArray() {
        String[] array = new String[ins.size()];
        ArrayList<String> arrayList = new ArrayList<>();
        for (Edge edge : ins) {
            arrayList.add(String.format("%s-%s", edge.getStart().getName(), edge.getWeight()));
        }
        return arrayList.toArray(array);
    }

    public String[] getOutsArray() {
        String[] array = new String[outs.size()];
        ArrayList<String> arrayList = new ArrayList<>();
        for (Edge edge : outs) {
            arrayList.add(String.format("%s-%s", edge.getEnd().getName(), edge.getWeight()));
        }
        return arrayList.toArray(array);
    }

    public void putNewOut(Edge edge) {
        outs.add(edge);
    }

    public void putNewIn(Edge edge) {
        ins.add(edge);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (name != null ? !name.equals(node.name) : node.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
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
}