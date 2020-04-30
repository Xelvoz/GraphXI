package graphs;

import graphs.algorithms.Flow;
import graphs.structure.types.FlowNetwork;

public class Main {
    public static void main(String[] args) {

//        FlowNetwork<Integer> graph = new FlowNetwork<>();
//        int s = 6;
//        int t = 7;
//        graph.addEdge(1, 2, 0, 5);
//        graph.addEdge(1, 4, 0, 10);
//        graph.addEdge(2, 4, 0, 8);
//        graph.addEdge(2, 3, 0, 5);
//        graph.addEdge(3, 2, 0, 10);
//        graph.addEdge(3, 5, 0, 15);
//        graph.addEdge(4, 7, 0, 25);
//        graph.addEdge(5, 7, 0, 6);
//        graph.addEdge(6, 1, 0, 16);
//        graph.addEdge(6, 3, 0, 13);

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
//        char s = 's';
//        char t = 't';
//        graph.addEdge('s', 'a', 0, 5);
//        graph.addEdge('s', 'b', 0, 7);
//        graph.addEdge('s', 'c', 0, 6);
//        graph.addEdge('a', 'x', 0, 4);
//        graph.addEdge('a', 'y', 0, 3);
//        graph.addEdge('b', 'y', 0, 4);
//        graph.addEdge('b', 'z', 0, 1);
//        graph.addEdge('c', 'z', 0, 5);
//        graph.addEdge('x', 't', 0, 3);
//        graph.addEdge('y', 't', 0, 7);
//        graph.addEdge('y', 'z', 0, 4);
//        graph.addEdge('z', 't', 0, 6);

//        FlowNetwork<Character> graph = new FlowNetwork<>();
//        char s = 's';
//        char t = 't';
//        graph.addEdge('s', 'u', 0, 10);
//        graph.addEdge('s', 'v', 0, 5);
//        graph.addEdge('u', 'v', 0, 15);
//        graph.addEdge('u', 't', 0, 5);
//        graph.addEdge('v', 't', 0, 10);

//        FlowNetwork<Character> graph = new FlowNetwork<>();
//        char s = 's';
//        char t = 't';
//        graph.addEdge('s', 'a', 0, 13);
//        graph.addEdge('s', 'b', 0, 10);
//        graph.addEdge('s', 'c', 0, 10);
//        graph.addEdge('a', 'x', 0, 24);
//        graph.addEdge('b', 'a', 0, 5);
//        graph.addEdge('b', 'c', 0, 15);
//        graph.addEdge('b', 'z', 0, 7);
//        graph.addEdge('c', 'z', 0, 15);
//        graph.addEdge('x', 'y', 0, 1);
//        graph.addEdge('x', 't', 0, 9);
//        graph.addEdge('y', 't', 0, 7);
//        graph.addEdge('y', 'z', 0, 6);
//        graph.addEdge('z', 't', 0, 16);
//
        FlowNetwork<String> graph = new FlowNetwork<>();
        String s = "s";
        String t = "t";
        graph.addEdge("s", "r1", 0, 1);
        graph.addEdge("s", "r2", 0, 1);
        graph.addEdge("s", "r3", 0, 1);
        graph.addEdge("s", "r4", 0, 1);
        graph.addEdge("s", "r5", 0, 1);
        graph.addEdge("r1", "c2", 0, 1);
        graph.addEdge("r1", "c3", 0, 1);
        graph.addEdge("r1", "c5", 0, 1);
        graph.addEdge("r2", "c1", 0, 1);
        graph.addEdge("r2", "c4", 0, 1);
        graph.addEdge("r2", "c5", 0, 1);
        graph.addEdge("r3", "c1", 0, 1);
        graph.addEdge("r3", "c4", 0, 1);
        graph.addEdge("r4", "c2", 0, 1);
        graph.addEdge("r4", "c3", 0, 1);
        graph.addEdge("r4", "c4", 0, 1);
        graph.addEdge("r4", "c5", 0, 1);
        graph.addEdge("r5", "c4", 0, 1);
        graph.addEdge("c1", "t", 0, 1);
        graph.addEdge("c2", "t", 0, 1);
        graph.addEdge("c3", "t", 0, 1);
        graph.addEdge("c4", "t", 0, 1);
        graph.addEdge("c5", "t", 0, 1);

        double maxFlow;
        maxFlow = Flow.EdmondsKarp(graph, s, t);
        System.out.println("Maximum Flow: " + maxFlow + "\n");

        maxFlow = 0.0;
        try {
            maxFlow = Flow.MinCostMaxFlow(graph, s, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Minimum Cost: " + maxFlow);
    }
}

/*
flows_01 mincut ||[(2,4), (5,7), (1,4)]||
flows_02 mincut ||[(1, 3), (2, 4)]||
Fig 5.12 mincut ||[(6, 5), (2, 1), (6, 1), (4, 1), (3, 1)]||
yellow nodes graph mincut ||[(s, a), (b, y), (c, z), (b, z)]||
wikipedia graph mincut ||[(s, u), (s, v)]||
unlabeled white nodes graph mincut ||[(x, y), (z, t), (x, t)]|| (same labels as yellow nodes graph)
 */