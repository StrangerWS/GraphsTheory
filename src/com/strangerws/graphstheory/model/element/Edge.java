package com.strangerws.graphstheory.model.element;

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
        if (this.start.compareTo(o.start) > 0 ||
                (this.start.equals(o.start) && (this.end.compareTo(o.end) > 0))) {
            return 1;
        } else if (this.start.compareTo(o.start) < 0 ||
                (this.start.equals(o.start) && (this.end.compareTo(o.end) < 0))) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
        if (start != null ? !start.equals(edge.start) : edge.start != null) return false;
        return !(end != null ? !end.equals(edge.end) : edge.end != null);

    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + weight;
        return result;
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
