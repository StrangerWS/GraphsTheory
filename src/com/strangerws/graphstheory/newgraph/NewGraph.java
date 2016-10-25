package com.strangerws.graphstheory.newgraph;

import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by DobryninAM on 11.10.2016.
 */

public class NewGraph {
    private ArrayList<NewNode> graph;

    public NewGraph(String fileName) {
        graph = new ArrayList<>();
        try (BufferedReader fs = new BufferedReader(new FileReader(fileName))) {
            String line;
            NewNode tmp;

            while ((line = fs.readLine()) != null) {
                String[] mas = line.split(" ");

                if (graph == null || (graph != null && (findNode(mas[0])) == null)) {
                    tmp = new NewNode(mas[0]);
                    NewNode search;
                    for (int i = 1; i <= mas.length; ++i) {
                        if ((search = findNode(mas[i])) == null) {
                            search = new NewNode(mas[i]);
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        } else {
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        }
                    }
                    graph.add(tmp);

                } else if (graph != null && (tmp = findNode(mas[0])) != null) {
                    NewNode search;
                    for (int i = 1; i < mas.length; ++i) {
                        if ((search = findNode(mas[i])) == null) {
                            search = new NewNode(mas[i]);
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        } else {
                            search.putNewIn(tmp);
                            tmp.putNewOut(search);
                        }
                    }
                    graph.add(tmp);
                }

            }

        } catch (IOException exc1) {
            System.out.println(exc1.getMessage());
        }

    }

    public NewGraph() {
        graph = new ArrayList<>(0);
    }

    public NewGraph(NewGraph anotherNewGraph) {
        graph = (ArrayList<NewNode>) anotherNewGraph.graph.clone();
    }

    @Nullable
    public NewNode findNode(String name) {
        for (NewNode node : graph) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void deleteNull() {
        for (int i = 0; i < graph.size(); ++i) {
            if (graph.get(i).ins.size() == 0 && graph.get(i).outs.size() ==0)
                graph.remove(graph.get(i));
        }
    }
}
