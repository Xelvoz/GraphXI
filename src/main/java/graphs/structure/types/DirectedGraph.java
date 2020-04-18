package graphs.structure.types;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.base.GraphType;

import java.util.Collection;

public class DirectedGraph<T> extends AbstractGraph<T> {

    public DirectedGraph() {
        super();
    }

    public DirectedGraph(Collection<T> vertices, Collection<Edge<T>> edges) {
        super(vertices, edges);
    }

    public DirectedGraph(DirectedGraph<T> graph) {
        super(graph);
    }

    @Override
    public boolean addEdge(T vertex, T other) {
        if (vertex != other) {
            addVertex(vertex);
            addVertex(other);
            return graph.get(vertex).add(other);
        }
        return false;
    }

    @Override
    public boolean removeEdge(T vertex, T other) {
        if (vertex != other) {
            if (graph.containsKey(vertex)) {
                return graph.get(vertex).remove(other);
            }
        }
        return false;
    }

    @Override
    public GraphType getType() {
        return GraphType.DIRECTED;
    }

}
