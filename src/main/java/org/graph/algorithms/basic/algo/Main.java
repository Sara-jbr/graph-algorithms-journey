package org.graph.algorithms.basic.algo;


import org.graph.algorithms.basic.model.DirectedGraph;
import org.graph.algorithms.basic.model.UndirectedGraph;
import org.graph.algorithms.basic.model.UnweightedGraph;
import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Graph Algorithm Menu =====");
        System.out.println("1. Unweighted Graph");
        System.out.println("2. Directed Graph");
        System.out.println("3. Undirected Graph");
        System.out.println("4. Weighted Graph");
        System.out.print("Choose graph type (1-4): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                runUnweightedGraph();
                break;
            case 2:
                runDirectedGraph();
                break;
            case 3:
                runUndirectedGraph();
                break;
            case 4:
                runWeightedGraph();
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }

    private static void runUnweightedGraph() {
        // Real-World Example: Social Network (Friendship relationships)
        UnweightedGraph graph = new UnweightedGraph();
        graph.addEdge("Alice", "Bob");
        graph.addEdge("Alice", "Charlie");
        graph.addEdge("Bob", "David");
        graph.addEdge("Charlie", "Eve");

        UnweightedGraphAlgorithms algo = new UnweightedGraphAlgorithms(graph.getGraph());

        System.out.println("\n--- Unweighted Undirected Graph (Social Network) ---");
        System.out.println("BFS from Alice:");
        algo.bfs("Alice");

        System.out.println("\nDFS from Alice:");
        algo.dfs("Alice");

        System.out.println("\nHas Cycle: " + algo.hasCycle()); // Cycle detection in social network
    }

    private static void runDirectedGraph() {
        // Real-World Example: Task Scheduling (Circular dependencies)
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("Task1", "Task2");
        graph.addEdge("Task2", "Task3");
        graph.addEdge("Task3", "Task1");  // Cycle

        DirectedGraphAlgorithms algo = new DirectedGraphAlgorithms(graph.getGraph());

        System.out.println("\n--- Directed Graph (Task Scheduling) ---");
        System.out.println("DFS from Task1:");
        algo.dfs("Task1");

        System.out.println("\nHas Cycle: " + algo.hasCycle());  // Detecting circular dependencies
    }

    private static void runUndirectedGraph() {
        // Real-World Example: Computer Network (Network devices)
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge("PC1", "PC2");
        graph.addEdge("PC2", "PC3");
        graph.addEdge("PC3", "PC4");
        graph.addEdge("PC4", "PC1");  // Cycle

        UndirectedGraphAlgorithms algo = new UndirectedGraphAlgorithms(graph.getGraph());

        System.out.println("\n--- Undirected Graph (Computer Network) ---");
        System.out.println("DFS from PC1:");
        algo.dfs("PC1");

        System.out.println("\nHas Cycle: " + algo.hasCycle());  // Detecting network redundancy
    }

    private static void runWeightedGraph() {
        // Real-World Example: Road Network (GPS navigation)
        WeightedGraph graph = new WeightedGraph();
        graph.addEdge("CityA", "CityB", 5);
        graph.addEdge("CityA", "CityC", 2);
        graph.addEdge("CityB", "CityD", 1);
        graph.addEdge("CityC", "CityD", 7);

        WeightedGraphAlgorithms algo = new WeightedGraphAlgorithms(graph.getGraph());

        System.out.println("\n--- Weighted Graph (Road Network) ---");
        System.out.println("BFS from CityA:");
        algo.bfs("CityA");

        System.out.println("\nDFS from CityA:");
        algo.dfs("CityA");

        System.out.println("\nHas Cycle: " + algo.hasCycle());  // Detecting cycles in the road network
    }
}

