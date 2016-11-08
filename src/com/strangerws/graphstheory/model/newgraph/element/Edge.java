package com.strangerws.graphstheory.model.newgraph.element;

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
    }

    @Override
    public int compareTo(Edge o) {
        if (this.start.getName().compareTo(o.start.getName()) > 0 ||
                (this.start.getName().equals(o.start.getName()) && (this.end.getName().compareTo(o.end.getName()) > 0))) {
            return 1;
        } else if (this.start.getName().compareTo(o.start.getName()) < 0 ||
                (this.start.getName().equals(o.start.getName()) && (this.end.getName().compareTo(o.end.getName()) < 0))) {
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

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
