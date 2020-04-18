package graphs.structure;

import java.util.Collection;
import java.util.HashMap;

public class FlowNetwork<T> extends DirectedGraph<T> {
    private HashMap<Edge<T>, Double> costs;
    private HashMap<Edge<T>, Double> capacities;
    private HashMap<Edge<T>, Double> flows;

    public FlowNetwork() {
        costs = new HashMap<>();
        capacities = new HashMap<>();
        flows = new HashMap<>();
    }

    public FlowNetwork(Collection<T> vertices, Collection<Edge<T>> edges) {
        super(vertices, edges);
        costs = new HashMap<>();
        capacities = new HashMap<>();
        flows = new HashMap<>();
    }

    public FlowNetwork(FlowNetwork<T> graph) {
        super(graph);
        costs = new HashMap<>();
        costs.putAll(graph.getCosts());
        capacities = new HashMap<>();
        capacities.putAll(graph.getCapacities());
        flows = new HashMap<>();
        flows.putAll(graph.getFlows());
    }

    public boolean addEdge(T vertex, T other, double flow, double cost, double capacity) {
        costs.putIfAbsent(new Edge<>(vertex, other), cost);
        capacities.putIfAbsent(new Edge<>(vertex, other), capacity);
        flows.putIfAbsent(new Edge<>(vertex, other), flow);
        return addEdge(vertex, other);
    }

    public boolean addEdge(T vertex, T other, double cost, double capacity) {
        return addEdge(vertex, other, 0.0, cost, capacity);
    }

    public boolean addEdge(Edge<T> edge, double flow, double cost, double capacity) {
        return addEdge(edge.getKey(), edge.getValue(), flow, cost, capacity);
    }

    public boolean addEdge(Edge<T> edge, double cost, double capacity) {
        return addEdge(edge.getKey(), edge.getValue(), cost, capacity);
    }

    public boolean addEdge(T vertex, T other, double capacity) {
        return addEdge(vertex, other, 0.0, capacity);
    }

    public boolean addEdge(Edge<T> edge, double capacity) {
        return addEdge(edge.getKey(), edge.getValue(), 0.0, capacity);
    }

    public double getCapacity(T vertex, T other) {
        return getCapacity(new Edge<>(vertex, other));
    }

    public double getCapacity(Edge<T> edge) {
        if (capacities.containsKey(edge)) {
            return capacities.get(edge);
        }
        return 0;
    }

    public double getFlow(T vertex, T other) {
        return getFlow(new Edge<>(vertex, other));
    }

    public double getFlow(Edge<T> edge) {
        if (flows.containsKey(edge)) {
            return flows.get(edge);
        }
        return 0;
    }

    public double getCost(T vertex, T other) {
        return getCost(new Edge<>(vertex, other));
    }

    public double getCost(Edge<T> edge) {
        if (costs.containsKey(edge)) {
            return costs.get(edge);
        }
        return 0;
    }

    public HashMap<Edge<T>, Double> getCosts() {
        return costs;
    }

    public HashMap<Edge<T>, Double> getCapacities() {
        return capacities;
    }

    public HashMap<Edge<T>, Double> getFlows() {
        return flows;
    }

    public double remainingCapacity(T u, T v) {
        return getCapacity(u, v) - getFlow(u, v);
    }

    public double remainingCapacity(Edge<T> edge) {
        return getCapacity(edge) - getFlow(edge);
    }

    public void printDetailed() {
        getAllEdges().forEach(
                e -> System.out.println(
                        String.format(
                                "~ (%s, %s) - Flow: %.0f - Capacity: %.0f - Cost: %.2f",
                                e.getKey(),
                                e.getValue(),
                                getFlow(e),
                                getCapacity(e),
                                getCost(e))
                )
        );
    }

    public void addFlow(Edge<T> edge, double pathFlow) {
        if (flows.containsKey(edge)) {
            flows.replace(edge, getFlow(edge) + pathFlow);
        } else {
            addEdge(edge, pathFlow, -getCost(edge.reverse()), getCapacity(edge.reverse()));
        }
    }

    public void updateCost(Edge<T> edge, double v) {
        if (costs.containsKey(edge)) {
            costs.replace(edge, v);
        } else {
            addEdge(edge, 0.0, v, getCapacity(edge.reverse()));
        }
    }
}
