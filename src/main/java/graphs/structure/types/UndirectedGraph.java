package graphs.structure.types;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.base.GraphType;

import java.util.Collection;
import java.util.HashSet;

public class UndirectedGraph<T> extends AbstractGraph<T> {

    public UndirectedGraph() {
        super();
    }

    public UndirectedGraph(Collection<T> vertices, Collection<Edge<T>> edges) {
        super(vertices, edges);
    }

    public UndirectedGraph(UndirectedGraph<T> graph) {
        super(graph);
    }

    @Override
    public boolean addEdge(T vertex, T other) {
        if (vertex != other) {
            addVertex(vertex);
            addVertex(other);
            graph.get(vertex).add(other);
            graph.get(other).add(vertex);
        }
        return false;
    }

    @Override
    public Collection<Edge<T>> getAllEdges() {
        HashSet<Edge<T>> edges = new HashSet<>();
        for (T v : getAllVertices()) {
            for (T u : getNeighbors(v)) {
                Edge<T> e = new Edge<>(v, u);
                if (!edges.contains(e.reverse())) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

    @Override
    public boolean removeEdge(T vertex, T other) {
        if (vertex != other) {
            if (graph.containsKey(vertex) && graph.containsKey(other)) {
                return graph.get(vertex).remove(other) && graph.get(other).remove(vertex);
            }
        }
        return false;
    }

    @Override
    public GraphType getType() {
        return GraphType.UNDIRECTED;
    }
}
