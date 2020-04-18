package graphs.algorithms;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.types.FlowNetwork;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {
    private ShortestPath() {
    }

    private static <T> HashMap<T, Double> initializeDistances(AbstractGraph<T> graph, double initialDistance) {
        return (HashMap<T, Double>) graph.getAllVertices().stream().collect(Collectors.toMap(
                (T v) -> v, (T v) -> initialDistance
        ));
    }

    public static <T> List<T> Djikstra(FlowNetwork<T> graph, T source, T sink, HashMap<T, Double> distances) {
        distances.clear();
        distances.putAll(initializeDistances(graph, Double.POSITIVE_INFINITY));
        distances.replace(source, 0.0);


        HashMap<T, T> predecessors = new HashMap<>();

        PriorityQueue<T> Q = new PriorityQueue<>(Comparator.comparing(
                (T v) -> v,
                Comparator.comparing(distances::get)
        ));

        Q.addAll(graph.getAllVertices());

        while (!Q.isEmpty()) {
            T u = Q.remove();
            for (T v : graph.getNeighbors(u)) {
                double cost = graph.getCost(u, v);
                double alt = distances.get(u) + cost;
                if (alt < distances.get(v) && graph.remainingCapacity(u, v) > 0) {
                    predecessors.put(v, u);
                    distances.replace(v, alt);
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
        if (distances.get(sink).isInfinite()) return new ArrayList<>();
        return Exploration.pathFromPredecessors(predecessors, source, sink);
    }

    public static <T> List<T> BellmanFord(FlowNetwork<T> graph, T source, T sink, HashMap<T, Double> distances) throws Exception {
        distances.clear();
        distances.putAll(initializeDistances(graph, Double.POSITIVE_INFINITY));
        HashMap<T, T> predecessors = new HashMap<>();

        distances.replace(source, 0.0);

        Collection<Edge<T>> allEdges = graph.getAllEdges();

        for (int i = 0; i < graph.getAllVertices().size() - 1; i++) {
            for (Edge<T> e : allEdges) {
                T u = e.getKey();
                T v = e.getValue();
                double newCost = distances.get(u) + graph.getCost(u, v);
                if (newCost < distances.get(v) && (graph.remainingCapacity(e) > 0)) {
                    distances.replace(v, newCost);
                    predecessors.put(v, u);
                }
            }
        }

        for (Edge<T> e : allEdges) {
            T u = e.getKey();
            T v = e.getValue();
            if (distances.get(u) + graph.getCost(u, v) < distances.get(v)) {
                throw new Exception("Negative cycle!");
            }
        }
        if (distances.get(sink).isInfinite()) return new ArrayList<>();
        return Exploration.pathFromPredecessors(predecessors, source, sink);
    }
}
