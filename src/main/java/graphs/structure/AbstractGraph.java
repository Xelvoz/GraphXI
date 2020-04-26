package graphs.structure;

import graphs.structure.base.Edge;
import graphs.structure.base.GraphType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public abstract class AbstractGraph<T> {
    protected ObservableMap<T, ObservableSet<T>> graph;

    public AbstractGraph() {
        graph = FXCollections.observableHashMap();
    }

    public AbstractGraph(Collection<T> vertices, Collection<Edge<T>> edges) {
        this();
        for (T v : vertices) addVertex(v);
        for (Edge<T> e : edges) addEdge(e);
    }

    public AbstractGraph(AbstractGraph<T> graph) {
        this();
        for (T v : graph.getAllVertices()) addVertex(v);
        for (Edge<T> e : graph.getAllEdges()) addEdge(e);
    }

    public boolean addVertex(T vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, FXCollections.observableSet(new HashSet<>()));
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
        return addEdge(edge.getFirstEnd(), edge.getSecondEnd());
    }

    public abstract boolean removeEdge(T vertex, T other);

    public boolean removeEdge(Edge<T> edge) {
        return removeEdge(edge.getFirstEnd(), edge.getSecondEnd());
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
        return getAllEdges().stream().filter(edge -> edge.getSecondEnd() == vertex).collect(Collectors.toList());
    }

    public Collection<Edge<T>> getOutgoingEdges(T vertex) {
        return getAllEdges().stream().filter(edge -> edge.getFirstEnd() == vertex).collect(Collectors.toList());
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

    public boolean hasNeighbor(T v, T u) {
        return graph.get(v).contains(u);
    }

    public int numberOfNodes() {
        return graph.size();
    }

    public int numberOfEdges() {
        return getAllEdges().size();
    }

    public ObservableMap<T, ObservableSet<T>> getGraph() {
        return graph;
    }
}