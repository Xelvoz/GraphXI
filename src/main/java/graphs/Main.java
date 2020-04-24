package graphs;

import graphs.algorithms.Flow;
import graphs.structure.types.FlowNetwork;

public class Main {
    public static void main(String[] args) {

        FlowNetwork<Integer> graph = new FlowNetwork<>();
        int s = 6;
        int t = 7;
        graph.addEdge(1, 2, 0, 5);
        graph.addEdge(1, 4, 0, 10);
        graph.addEdge(2, 4, 0, 8);
        graph.addEdge(2, 3, 0, 5);
        graph.addEdge(3, 2, 0, 10);
        graph.addEdge(3, 5, 0, 15);
        graph.addEdge(4, 7, 0, 25);
        graph.addEdge(5, 7, 0, 6);
        graph.addEdge(6, 1, 0, 16);
        graph.addEdge(6, 3, 0, 13);

//        FlowNetwork<Integer> graph = new FlowNetwork<>();
//        int s = 6;
//        int t = 5;
//        graph.addEdge(6, 1, 0, 10);
//        graph.addEdge(6, 2, 0, 8);
//        graph.addEdge(1, 2, 0, 5);
//        graph.addEdge(1, 3, 0, 5);
//        graph.addEdge(2, 1, 0, 4);
//        graph.addEdge(2, 4, 0, 10);
//        graph.addEdge(3, 2, 0, 7);
//        graph.addEdge(3, 4, 0, 6);
//        graph.addEdge(3, 5, 0, 3);
//        graph.addEdge(4, 3, 0, 10);
//        graph.addEdge(4, 5, 0, 14);

//        FlowNetwork<Integer> graph = new FlowNetwork<>();
//        int s = 6;
//        int t = 1;
//        graph.addEdge(6, 5, 0, 0.0);
//        graph.addEdge(6, 4, 0, 8.0);
//        graph.addEdge(6, 3, 0, 12.0);
//        graph.addEdge(6, 2, 0, 14.0);
//        graph.addEdge(6, 1, 0, 15.0);
//        graph.addEdge(5, 4, 0, 0.0);
//        graph.addEdge(5, 3, 0, 4.0);
//        graph.addEdge(5, 2, 0, 6.0);
//        graph.addEdge(5, 1, 0, 7.0);
//        graph.addEdge(4, 3, 0, 0.0);
//        graph.addEdge(4, 2, 0, 2.0);
//        graph.addEdge(4, 1, 0, 3.0);
//        graph.addEdge(3, 2, 0, 0.0);
//        graph.addEdge(3, 1, 0, 1.0);
//        graph.addEdge(2, 1, 0, 0.0);

//        FlowNetwork<Character> graph = new FlowNetwork<>();
//        int s = 's';
//        int t = 't';
//        graph.addEdge((int) 's', (int) 'a', 0, 5);
//        graph.addEdge((int) 's', (int) 'b', 0, 7);
//        graph.addEdge((int) 's', (int) 'c', 0, 6);
//        graph.addEdge((int) 'a', (int) 'x', 0, 4);
//        graph.addEdge((int) 'a', (int) 'y', 0, 3);
//        graph.addEdge((int) 'b', (int) 'y', 0, 4);
//        graph.addEdge((int) 'b', (int) 'z', 0, 1);
//        graph.addEdge((int) 'c', (int) 'z', 0, 5);
//        graph.addEdge((int) 'x', (int) 't', 0, 3);
//        graph.addEdge((int) 'y', (int) 't', 0, 7);
//        graph.addEdge((int) 'y', (int) 'z', 0, 4);
//        graph.addEdge((int) 'z', (int) 't', 0, 6);

//        FlowNetwork<Character> graph = new FlowNetwork<>();
//        int s = 's';
//        int t = 't';
//        graph.addEdge((int) 's', (int) 'u', 0, 10);
//        graph.addEdge((int) 's', (int) 'v', 0, 5);
//        graph.addEdge((int) 'u', (int) 'v', 0, 15);
//        graph.addEdge((int) 'u', (int) 't', 0, 5);
//        graph.addEdge((int) 'v', (int) 't', 0, 10);

//        FlowNetwork<Character> graph = new FlowNetwork<>();
//        int s = 's';
//        int t = 't';
//        graph.addEdge((int) 's', (int) 'a', 0, 13);
//        graph.addEdge((int) 's', (int) 'b', 0, 10);
//        graph.addEdge((int) 's', (int) 'c', 0, 10);
//        graph.addEdge((int) 'a', (int) 'x', 0, 24);
//        graph.addEdge((int) 'b', (int) 'a', 0, 5);
//        graph.addEdge((int) 'b', (int) 'c', 0, 15);
//        graph.addEdge((int) 'b', (int) 'z', 0, 7);
//        graph.addEdge((int) 'c', (int) 'z', 0, 15);
//        graph.addEdge((int) 'x', (int) 'y', 0, 1);
//        graph.addEdge((int) 'x', (int) 't', 0, 9);
//        graph.addEdge((int) 'y', (int) 't', 0, 7);
//        graph.addEdge((int) 'y', (int) 'z', 0, 6);
//        graph.addEdge((int) 'z', (int) 't', 0, 16);

        graph.printDetailed();

        double maxFlow;
        maxFlow = Flow.EdmondsKarp(graph, s, t);
        System.out.println("Maximum Flow: " + maxFlow);

        maxFlow = 0.0;
        try {
            maxFlow = Flow.MinCostMaxFlow(graph, s, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Minimum Cost: " + maxFlow);

    }
}
