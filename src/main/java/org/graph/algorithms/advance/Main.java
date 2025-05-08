package org.graph.algorithms.advance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //TarjanSCC algorithm
        TarjanSCC tarjan = new TarjanSCC(8);

        tarjan.addEdge(0, 1);
        tarjan.addEdge(1, 2);
        tarjan.addEdge(2, 0);
        tarjan.addEdge(3, 1);
        tarjan.addEdge(3, 2);
        tarjan.addEdge(3, 4);
        tarjan.addEdge(4, 5);
        tarjan.addEdge(5, 6);
        tarjan.addEdge(6, 4);
        tarjan.addEdge(6, 7);

        List<List<Integer>> sccs = tarjan.findSCCs();
        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }


        //EdmondsKarp algorithm
        EdmondsKarp ek = new EdmondsKarp(6);

        ek.addEdge(0, 1, 16);
        ek.addEdge(0, 2, 13);
        ek.addEdge(1, 2, 10);
        ek.addEdge(1, 3, 12);
        ek.addEdge(2, 1, 4);
        ek.addEdge(2, 4, 14);
        ek.addEdge(3, 2, 9);
        ek.addEdge(3, 5, 20);
        ek.addEdge(4, 3, 7);
        ek.addEdge(4, 5, 4);

        int maxFlow = ek.maxFlow(0, 5);
        System.out.println("Maximum Flow: " + maxFlow);


        //AStar algorithm
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F"));
        graph.put("D", Arrays.asList("B"));
        graph.put("E", Arrays.asList("B", "F"));
        graph.put("F", Arrays.asList("C", "E", "G"));
        graph.put("G", Arrays.asList("F"));

        Map<String, Integer> heuristic = new HashMap<>();
        heuristic.put("A", 6);
        heuristic.put("B", 5);
        heuristic.put("C", 5);
        heuristic.put("D", 3);
        heuristic.put("E", 2);
        heuristic.put("F", 1);
        heuristic.put("G", 0); // goal

        List<String> path = AStar.findPath(graph, "A", "G", heuristic);
        System.out.println("Shortest path from A to G: " + path);
    }
}
