package org.graph.algorithms.basic.algo;

import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.*;

public class WeightedGraphAlgorithms {
    private final Map<String, List<WeightedGraph.Edge>> graph;

    public WeightedGraphAlgorithms(Map<String, List<WeightedGraph.Edge>> graph) {
        this.graph = graph;
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.println(node);

            for (WeightedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                String neighbor = edge.getTo();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(String node, Set<String> visited) {
        visited.add(node);
        System.out.println(node);

        for (WeightedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            String neighbor = edge.getTo();
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleDFS(node, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleDFS(String current, Set<String> visited, String parent) {
        visited.add(current);

        for (WeightedGraph.Edge edge : graph.getOrDefault(current, Collections.emptyList())) {
            String neighbor = edge.getTo();
            if (!visited.contains(neighbor)) {
                if (hasCycleDFS(neighbor, visited, current)) {
                    return true;
                }
            } else if (!neighbor.equals(parent)) {
                return true;
            }
        }
        return false;
    }
}
