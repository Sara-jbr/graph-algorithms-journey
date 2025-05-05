package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.DirectedGraph;
import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // ---------------- TOPOLOGICAL SORT (DFS) ------------------
        DirectedGraph topologicalSort = new DirectedGraph();
        topologicalSort.addEdge("5", "0");
        topologicalSort.addEdge("5", "2");
        topologicalSort.addEdge("4", "0");
        topologicalSort.addEdge("4", "1");
        topologicalSort.addEdge("2", "3");
        topologicalSort.addEdge("3", "1");

        System.out.println("Topological Sort (DFS): " + TopologicalSort.sort(topologicalSort));
        // Output: Topological Sort (DFS): [5, 4, 2, 3, 1, 0]

        // ---------------- TOPOLOGICAL SORT (BFS / KAHN's ALGORITHM) ------------------
        DirectedGraph topologicalSortBFSGraph = new DirectedGraph();
        topologicalSortBFSGraph.addEdge("5", "0");
        topologicalSortBFSGraph.addEdge("5", "2");
        topologicalSortBFSGraph.addEdge("4", "0");
        topologicalSortBFSGraph.addEdge("4", "1");
        topologicalSortBFSGraph.addEdge("2", "3");
        topologicalSortBFSGraph.addEdge("3", "1");

        System.out.println("Topological Sort (BFS): " + TopologicalSortBFS.sort(topologicalSortBFSGraph));
        // Output: Topological Sort (BFS): [4, 5, 2, 3, 1, 0]

        // ---------------- DIJKSTRA'S ALGORITHM ------------------
        WeightedGraph dijkstraGraph = new WeightedGraph();
        dijkstraGraph.addEdge("A", "B", 4);
        dijkstraGraph.addEdge("A", "C", 2);
        dijkstraGraph.addEdge("B", "C", 5);
        dijkstraGraph.addEdge("B", "D", 10);
        dijkstraGraph.addEdge("C", "D", 3);
        dijkstraGraph.addEdge("D", "E", 7);
        dijkstraGraph.addEdge("E", "B", 1);

        Map<String, Integer> distances = DijkstraAlgorithm.dijkstra(dijkstraGraph, "A");
        System.out.println("Shortest distances from A (Dijkstra): " + distances);
        // Output: Shortest distances from A (Dijkstra): {A=0, B=4, C=2, D=5, E=12}

        List<String> path = DijkstraAlgorithm.getPath(new HashMap<>(), "E");
        System.out.println("Shortest path from A to E: " + path);
        // Output: Shortest path from A to E: []  (since you're passing an empty parent map)

        // ---------------- BELLMAN-FORD ALGORITHM ------------------
        try {
            Map<String, Integer> bellmanDistances = BellmanFord.shortestPaths(dijkstraGraph, "A");
            System.out.println("Shortest distances from A (Bellman-Ford): " + bellmanDistances);
            // Output: Shortest distances from A (Bellman-Ford): {A=0, B=4, C=2, D=5, E=12}

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // ---------------- FLOYD-WARSHALL ALGORITHM ------------------
        Map<String, Map<String, Integer>> floydPaths = FloydWarshall.shortestPaths(dijkstraGraph);
        System.out.println("Floyd-Warshall all-pairs shortest paths:");
        for (String from : floydPaths.keySet()) {
            System.out.println("From " + from + ": " + floydPaths.get(from));
        }
        /*
        Output:
        Floyd-Warshall all-pairs shortest paths:
        From A: {A=0, B=4, C=2, D=5, E=12}
        From B: {B=0, C=5, D=8, E=15}
        From C: {C=0, D=3, E=10, B=11}
        From D: {D=0, E=7, B=8, C=16}
        From E: {E=0, B=1, C=6, D=9}
        */

// ---------------- KRUSKAL'S ALGORITHM ------------------
        Set<String> vertices = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));
        List<WeightedGraph.Edge> edges = Arrays.asList(
                new WeightedGraph.Edge("A", "B", 1),
                new WeightedGraph.Edge("A", "C", 3),
                new WeightedGraph.Edge("B", "C", 1),
                new WeightedGraph.Edge("B", "D", 2),
                new WeightedGraph.Edge("C", "D", 3),
                new WeightedGraph.Edge("D", "E", 4)
        );

        List<WeightedGraph.Edge> mstKruskal = KruskalMST.kruskal(edges, new ArrayList<>(vertices));
        System.out.println("Minimum Spanning Tree (Kruskal):");
        for (WeightedGraph.Edge edge : mstKruskal) {
            System.out.println(edge.getFrom() + " - " + edge.getTo() + " : " + edge.getWeight());
        }        /*
        Output:
        Minimum Spanning Tree (Kruskal):
        A - B : 1
        B - C : 1
        B - D : 2
        D - E : 4
        */

        // ---------------- PRIM'S ALGORITHM ------------------
        List<WeightedGraph.Edge> mstPrim = PrimsMST.prim(dijkstraGraph, "A");
        System.out.println("Minimum Spanning Tree (Prim):");
        for (WeightedGraph.Edge edge : mstPrim) {
            System.out.println(edge.getFrom() + " - " + edge.getTo() + " : " + edge.getWeight());
        }/*
        Output:
        Minimum Spanning Tree (Prim):
        A - C : 2
        A - B : 4
        C - D : 3
        D - E : 7
        */


        // ---------------- UNION FIND'S ALGORITHM ------------------

        UnionFind uf = new UnionFind();

        // Union operations
        uf.union("A", "B");
        uf.union("B", "C");
        uf.union("D", "E");

        // Find operations
        System.out.println("Find A: " + uf.find("A")); // Should return same root for A, B, C
        System.out.println("Find B: " + uf.find("B"));
        System.out.println("Find C: " + uf.find("C"));

        System.out.println("Find D: " + uf.find("D")); // Should return same root for D, E
        System.out.println("Find E: " + uf.find("E"));

        // Check if A and E are in the same set
        System.out.println("Is A connected to E? " + uf.find("A").equals(uf.find("E"))); // false

        // Now connect the two sets
        uf.union("C", "D");

        System.out.println("After connecting C and D...");
        System.out.println("Is A connected to E? " + uf.find("A").equals(uf.find("E"))); // true
    }
}