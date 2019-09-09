package com.michael.graph.impl;

import com.michael.graph.Edge;
import com.michael.graph.Graph;

import java.util.*;

public class GraphImpl<T> implements Graph<T> {

    private Map<T, Set<T>> graph = new HashMap<>();
    private final boolean isDirected;

    public GraphImpl(boolean isDirected) {
        this.isDirected = isDirected;
    }

    @Override
    public void addVertex(T vertex) {
        Objects.requireNonNull(vertex, "vertex.invalid");
        graph.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(T start, T end) {
        checkNull(start, end);
        add(start, end);
        if (!isDirected) {
            add(end, start);
        }
    }

    @Override
    public List<Edge<T>> getPath(T start, T end) {
        checkNull(start, end);
        if (start.equals(end)){
            return Collections.singletonList(new Edge<>(start, end));
        }
        Queue<T> queue = new LinkedList<>();
        Map<T, T> path = new HashMap<>();
        queue.add(start);
        while (true) {
            T current = queue.poll();
            Set<T> siblings = graph.getOrDefault(current, new HashSet<>());
            for (T sibling : siblings) {
                if (path.containsKey(sibling)) {
                    continue;
                } else {
                    queue.add(sibling);
                    path.put(sibling, current);
                }
                if (sibling.equals(end)) {
                    return createResultPath(path, start, end);
                }
            }
            if (queue.isEmpty()) {
                return new ArrayList<>();
            }
        }
    }

    private void checkNull(T start, T end) {
        Objects.requireNonNull(start, "start.vertex.invalid");
        Objects.requireNonNull(end, "end.vertex.invalid");
    }

    private List<Edge<T>> createResultPath(Map<T, T> path, T start, T end) {
        T current = end;
        LinkedList<Edge<T>> result = new LinkedList<>();
        while (true) {
            T previous = path.get(current);
            result.addFirst(new Edge<>(previous, current));
            if (previous.equals(start)) {
                return result;
            }
            current = previous;
        }
    }

    private void add(T start, T end) {
        if (graph.containsKey(start)) {
            graph.get(start).add(end);
        } else {
            Set<T> siblings = new HashSet<>();
            siblings.add(end);
            graph.put(start, siblings);
        }
    }
}
