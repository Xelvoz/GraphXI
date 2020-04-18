package graphs.structure;

import java.util.Objects;

public class Edge<T> {
    private T v;
    private T u;

    public Edge(T v, T u) {
        this.v = v;
        this.u = u;
    }

    public Edge<T> reverse() {
        return new Edge<>(u, v);
    }

    public T getKey() {
        return v;
    }

    public T getValue() {
        return u;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", v, u);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return v.equals(edge.v) &&
                u.equals(edge.u);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, u);
    }
}
