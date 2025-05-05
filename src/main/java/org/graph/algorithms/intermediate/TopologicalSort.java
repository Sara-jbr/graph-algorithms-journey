package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.DirectedGraph;

import java.util.*;

public class TopologicalSort {

    public static List<String> sort(DirectedGraph graph) {
        Map<String, List<DirectedGraph.Edge>> adjList = graph.getGraph();
        Set<String> visited = new HashSet<>();
        Set<String> visiting = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : adjList.keySet()) {
            if (!visited.contains(node)) {
                if (!dfs(node, adjList, visited, visiting, stack)) {
                    throw new RuntimeException("Graph has a cycle. Topological sort not possible.");
                }
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static boolean dfs(
            String node,
            Map<String, List<DirectedGraph.Edge>> adjList,
            Set<String> visited,
            Set<String> visiting,
            Stack<String> stack) {

        visiting.add(node);

        for (DirectedGraph.Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
            String neighbor = edge.getTo();
            if (visited.contains(neighbor)) continue;
            if (visiting.contains(neighbor)) return false;

            if (!dfs(neighbor, adjList, visited, visiting, stack)) return false;
        }

        visiting.remove(node);
        visited.add(node);
        stack.push(node);
        return true;
    }
}
