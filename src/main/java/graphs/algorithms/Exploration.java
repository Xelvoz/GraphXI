package graphs.algorithms;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.types.FlowNetwork;

import java.util.*;
import java.util.stream.Collectors;

public class Exploration {
    private Exploration() {
    }

    public static <T> HashMap<T, Boolean> visitedMap(AbstractGraph<T> graph) {
        return (HashMap<T, Boolean>) graph.getAllVertices().stream().collect(Collectors.toMap(
                (T v) -> v, (T v) -> Boolean.FALSE
        ));
    }

    public static <T> Map<T, Boolean> Bfs(AbstractGraph<T> graph, T source) {
        HashMap<T, Boolean> visited = visitedMap(graph);
        Queue<T> Q = new LinkedList<>();
        Q.add(source);
        visited.replace(source, true);
        while (!Q.isEmpty()) {
            T v = Q.remove();
            for (T u : graph.getNeighbors(v)) {
                if (!visited.get(u)) {
                    visited.replace(u, true);
                    Q.add(u);
                }
            }
        }
        return visited;
    }

    public static <T> List<T> Dfs(FlowNetwork<T> graph, T source, T sink) {
        HashMap<T, T> visited = new HashMap<>();
        Stack<T> Q = new Stack<>();
        Q.add(source);
        visited.put(source, source);
        while (!Q.isEmpty()) {
            T v = Q.pop();
            for (T u : graph.getNeighbors(v)) {
                if (!visited.containsKey(u) && (graph.getCapacity(v, u) > graph.getFlow(v, u))) {
                    visited.put(u, v);
                    Q.push(u);
                }
            }
        }
        return pathFromPredecessors(visited, source, sink);
    }

    public static <T> Map<T, Boolean> Dfs(AbstractGraph<T> graph, T source) {
        HashMap<T, Boolean> visited = visitedMap(graph);
        Stack<T> Q = new Stack<>();
        Q.add(source);
        visited.replace(source, true);
        while (!Q.isEmpty()) {
            T v = Q.pop();
            for (T u : graph.getNeighbors(v)) {
                if (!visited.get(u)) {
                    visited.replace(u, true);
                    Q.push(u);
                }
            }
        }
        return visited;
    }

    public static <T> Map<T, Boolean> Dfs(FlowNetwork<T> graph, T source) {
        HashMap<T, Boolean> visited = visitedMap(graph);
        Stack<T> Q = new Stack<>();
        Q.add(source);
        visited.replace(source, true);
        while (!Q.isEmpty()) {
            T v = Q.pop();
            for (T u : graph.getNeighbors(v)) {
                if (!visited.get(u) && graph.remainingCapacity(v, u) > 0) {
                    visited.replace(u, true);
                    Q.push(u);
                }
            }
        }
        return visited;
    }

    public static <T> List<Edge<T>> verticesToEdges(List<T> vertices) {
        List<Edge<T>> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            edges.add(new Edge<>(vertices.get(i), vertices.get(i + 1)));
        }
        return edges;
    }

    public static <T> List<T> pathFromPredecessors(HashMap<T, T> predecessors, T source, T sink) {
        LinkedList<T> path = new LinkedList<>();
        if (predecessors.containsKey(sink)) {
            T v = sink;
            while (v != source) {
                path.addFirst(v);
                v = predecessors.get(v);
            }
            path.addFirst(source);
        }
        return path;
    }
}
