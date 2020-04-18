package graphs;

import graphs.algorithms.Flow;
import graphs.structure.FlowNetwork;

public class Main {
    public static void main(String[] args) {
        int s = 0;
        int t = 9;
        FlowNetwork<Integer> graph = new FlowNetwork<>();
        graph.addEdge(s, 1, 1, 5);
        graph.addEdge(s, 2, 1, 8);
        graph.addEdge(s, 3, 1, 3);
        graph.addEdge(s, 4, 1, 3);
        graph.addEdge(s, 5, 1, 7);
        graph.addEdge(s, 9, 1, 7);
        graph.addEdge(1, 9, 1, 4);
        graph.addEdge(2, 9, 1, 9);
        graph.addEdge(3, 6, 1, 1);
        graph.addEdge(6, 9, 1, 1);
        graph.addEdge(4, 7, 1, 4);
        graph.addEdge(7, 9, 1, 6);
        graph.addEdge(5, 8, 1, 6);
        graph.addEdge(8, 9, 1, 5);

        graph.printDetailed();

        double maxFlow = 0.0;
        maxFlow = Flow.EdmondsKarp(graph, s, t);
        System.out.println("Maximum Flow: " + maxFlow);

        maxFlow = 0.0;
        try {
            maxFlow = Flow.MinCostMaxFlow(graph, s, t);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Minimum Cost: " + maxFlow);

    }
}
