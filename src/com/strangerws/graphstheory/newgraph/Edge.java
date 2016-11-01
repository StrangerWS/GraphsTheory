package com.strangerws.graphstheory.newgraph;

/**
 * Created by User on 13.10.2016.
 */
public class Edge implements Comparable<Edge> {
    Node start;
    Node end;
    int weight;
    boolean isArc;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
        isArc = true;
    }

    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        isArc = true;
    }

    public Edge(Node start, Node end, int weight, boolean isArc) {

        this.start = start;
        this.end = end;
        this.weight = weight;
        this.isArc = isArc;
        isArc = true;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.start.name.compareTo(o.start.name) == 1 ||
                (this.start.name.equals(o.start.name) && (this.end.name.compareTo(o.end.name) == 1))) {
            return 1;
        } else if (this.start.name.compareTo(o.start.name) == -1 ||
                (this.start.name.equals(o.start.name) && (this.end.name.compareTo(o.end.name) == -1))) {
            return -1;
        } else return 0;
    }

    public boolean checkIfArc(Edge anotherEdge) {
        if (this.start == anotherEdge.end && this.end == anotherEdge.start) {
            isArc = false;
            anotherEdge.isArc = false;
            return false;
        }
        return true;
    }
}
