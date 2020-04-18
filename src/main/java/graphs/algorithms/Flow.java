package graphs.algorithms;

import graphs.structure.base.Edge;
import graphs.structure.types.FlowNetwork;

import java.util.HashMap;
import java.util.List;

public class Flow {
    private Flow() {
    }

    public static <T> double EdmondsKarp(FlowNetwork<T> graph, T source, T sink) {
        FlowNetwork<T> residualGraph = new FlowNetwork<>(graph);
        double maxFlow = 0;
        List<T> path;
        while (!(path = Exploration.Dfs(residualGraph, source, sink)).isEmpty()) {
            double pathFlow = Double.POSITIVE_INFINITY;
            // Find minimum flow for this path.
            List<Edge<T>> edges = Exploration.verticesToEdges(path);
            for (Edge<T> edge : edges) {
                pathFlow = Math.min(pathFlow, residualGraph.remainingCapacity(edge));
            }
            // Update Flow
            for (Edge<T> edge : edges) {
                residualGraph.addFlow(edge, pathFlow);
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static <T> double MinCostMaxFlow(FlowNetwork<T> graph, T source, T sink) throws Exception {
        FlowNetwork<T> residualGraph = new FlowNetwork<>(graph);
        HashMap<T, Double> distances = new HashMap<>();
        double minCost = 0;
        double maxFlow = 0;

        // Using BellmanFord will detect negative cycles from the start.
        List<T> path = ShortestPath.BellmanFord(residualGraph, source, sink, distances);
        // Reduce costs
        reduceCosts(residualGraph, distances);

        while (!path.isEmpty()) {
            double pathFlow = Double.POSITIVE_INFINITY;
            // Find minimum flow for this path.
            List<Edge<T>> edges = Exploration.verticesToEdges(path);
            for (Edge<T> edge : edges) {
                pathFlow = Math.min(pathFlow, residualGraph.remainingCapacity(edge));
            }
            // Update Flow
            for (Edge<T> edge : edges) {
                residualGraph.addFlow(edge, pathFlow);
                minCost += pathFlow * graph.getCost(edge);
            }
            // Reduce costs
            reduceCosts(residualGraph, distances);
            // Djikstra on the reduced costs
            path = ShortestPath.Djikstra(residualGraph, source, sink, distances);
            maxFlow += pathFlow;
        }
        return minCost;
    }

    private static <T> void reduceCosts(FlowNetwork<T> graph, HashMap<T, Double> distances) {
        for (Edge<T> edge : graph.getAllEdges()) {
            Double du = distances.get(edge.getKey());
            Double dv = distances.get(edge.getValue());
            if (graph.remainingCapacity(edge) > 0) {
                graph.updateCost(edge, graph.getCost(edge) + du - dv);
            } else {
                graph.updateCost(edge, 0.0);
            }
        }
    }
}
