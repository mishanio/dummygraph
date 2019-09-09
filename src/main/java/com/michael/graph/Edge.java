package com.michael.graph;

import java.util.Objects;

public class Edge<T> {
    private final T start;
    private final T end;

    public Edge(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(start, edge.start) &&
                Objects.equals(end, edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
