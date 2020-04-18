package graphs.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public abstract class AbstractGraph<T> {
    protected HashMap<T, HashSet<T>> graph;

    AbstractGraph() {
        graph = new HashMap<>();
    }

    AbstractGraph(Collection<T> vertices, Collection<Edge<T>> edges) {
        this();
        for (T v : vertices) addVertex(v);
        for (Edge<T> e : edges) addEdge(e);
    }

    AbstractGraph(AbstractGraph<T> graph) {
        this();
        for (T v : graph.getAllVertices()) addVertex(v);
        for (Edge<T> e : graph.getAllEdges()) addEdge(e);
    }

    public boolean addVertex(T vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new HashSet<>());
            return true;
        }
        return false;
    }

    public boolean removeVertex(T vertex) {
        if (graph.containsKey(vertex)) {
            graph.remove(vertex);
            graph.keySet().forEach(v -> graph.get(v).remove(vertex));
            return true;
        }
        return false;
    }

    public abstract boolean addEdge(T vertex, T other);

    public boolean addEdge(Edge<T> edge) {
        return addEdge(edge.getKey(), edge.getValue());
    }

    public abstract boolean removeEdge(T vertex, T other);

    public boolean removeEdge(Edge<T> edge) {
        return removeEdge(edge.getKey(), edge.getValue());
    }

    public Collection<T> getNeighbors(T vertex) {
        if (graph.containsKey(vertex)) return graph.get(vertex);
        return new HashSet<>();
    }

    public Collection<T> getAllVertices() {
        return graph.keySet();
    }

    public Collection<Edge<T>> getAllEdges() {
        HashSet<Edge<T>> edges = new HashSet<>();
        for (T v : getAllVertices()) {
            for (T u : getNeighbors(v)) {
                edges.add(new Edge<>(v, u));
            }
        }
        return edges;
    }

    public Collection<Edge<T>> getIncomingEdges(T vertex) {
        return getAllEdges().stream().filter(edge -> edge.getValue() == vertex).collect(Collectors.toList());
    }

    public Collection<Edge<T>> getOutgoingEdges(T vertex) {
        return getAllEdges().stream().filter(edge -> edge.getKey() == vertex).collect(Collectors.toList());
    }

    public void print() {
        for (T v : getAllVertices()) {
            System.out.print(String.format("~ %s - { ", v.toString()));
            for (T u : getNeighbors(v)) {
                System.out.print(String.format("%s ", u.toString()));
            }
            System.out.println("}");
        }
    }


    public abstract GraphType getType();
}