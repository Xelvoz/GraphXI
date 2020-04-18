package graphs.structure.generators;

import graphs.structure.base.Vertex;
import graphs.structure.types.UndirectedGraph;

public class UGGenerator {
    private UGGenerator() {
    }

    public static UndirectedGraph<Vertex> chain(int n) {
        UndirectedGraph<Vertex> graph = new UndirectedGraph<>();
        if (n < 0) throw new RuntimeException("The number of nodes must be positive.");
        else if (n == 1) {
            graph.addVertex(new Vertex(0));
        } else {
            for (int i = 0; i < n - 1; i++) {
                graph.addEdge(new Vertex(i), new Vertex(i + 1));
            }
        }
        return graph;
    }

    public static UndirectedGraph<Vertex> cyclic(int n) {
        UndirectedGraph<Vertex> chain = chain(n);
        if (n >= 3) {
            chain.addEdge(new Vertex(0), new Vertex(n - 1));
        }
        return chain;
    }
}
