package org.graph.algorithms.basic.algo;

import org.graph.algorithms.basic.model.UnweightedGraph;

import java.util.*;

public class UnweightedGraphAlgorithms {
    private final Map<String, List<UnweightedGraph.Edge>> graph;

    public UnweightedGraphAlgorithms(Map<String, List<UnweightedGraph.Edge>> graph) {
        this.graph = graph;
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.println(node);

            for (UnweightedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(edge.getTo())) {
                    visited.add(edge.getTo());
                    queue.add(edge.getTo());
                }
            }
        }
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsUtil(start, visited);
    }

    private void dfsUtil(String node, Set<String> visited) {
        visited.add(node);
        System.out.println(node);

        for (UnweightedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(edge.getTo())) {
                dfsUtil(edge.getTo(), visited);
            }
        }
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleUtil(node, visited, null)) return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(String node, Set<String> visited, String parent) {
        visited.add(node);
        for (UnweightedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            String neighbor = edge.getTo();
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(neighbor, visited, node)) return true;
            } else if (!neighbor.equals(parent)) {
                return true;
            }
        }
        return false;
    }
}
