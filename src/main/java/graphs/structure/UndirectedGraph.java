package graphs.structure;

import java.util.Collection;

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
