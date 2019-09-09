package com.michael.graph;

import java.util.List;

public interface Graph<T> {

    List<Edge<T>> getPath(T start, T end);

    void addVertex(T vertex);

    default void addEdge(Edge<T> edge) {
        addEdge(edge.getStart(), edge.getEnd());
    }

     void addEdge(T start, T end);
}
