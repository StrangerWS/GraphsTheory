package com.strangerws.graphstheory.newgraph;

/**
 * Created by User on 13.10.2016.
 */
public class NewEdge implements Comparable<NewEdge> {
    NewNode start;
    NewNode end;
    int weight;
    boolean isArc;

    public NewEdge(NewNode start, NewNode end) {
        this.start = start;
        this.end = end;
        isArc = true;
    }
    public NewEdge(NewNode start, NewNode end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        isArc = true;
    }
    public NewEdge(NewNode start, NewNode end, int weight, boolean isArc) {

        this.start = start;
        this.end = end;
        this.weight = weight;
        this.isArc = isArc;
        isArc = true;
    }

    @Override
    public int compareTo(NewEdge o) {
        if (this.start.name.compareTo(o.start.name) == 1 ||
                (this.start.name.equals(o.start.name) && (this.end.name.compareTo(o.end.name) == 1))) {
            return 1;
        } else if (this.start.name.compareTo(o.start.name) == -1 ||
                (this.start.name.equals(o.start.name) && (this.end.name.compareTo(o.end.name) == -1))) {
            return -1;
        } else return 0;
    }
    public boolean checkIfArc(NewEdge anotherNewEdge){
        if (this.start == anotherNewEdge.end && this.end == anotherNewEdge.start){
            isArc = false;
            anotherNewEdge.isArc = false;
            return false;
        }
        return true;
    }
}
