package org.graph.algorithms.basic.algo;
import org.graph.algorithms.basic.model.UndirectedGraph;

import java.util.*;

public class UndirectedGraphAlgorithms {
    private final Map<String, List<UndirectedGraph.Edge>> graph;

    public UndirectedGraphAlgorithms(Map<String, List<UndirectedGraph.Edge>> graph) {
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

            for (UndirectedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(edge.getTo())) {
                    visited.add(edge.getTo());
                    queue.add(edge.getTo());
                }
            }
        }
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited);
    }

    private void dfsHelper(String node, Set<String> visited) {
        if (visited.contains(node)) return;
        visited.add(node);
        System.out.println(node);

        for (UndirectedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            dfsHelper(edge.getTo(), visited);
        }
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (hasCycleHelper(node, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleHelper(String node, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        for (UndirectedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            if (hasCycleHelper(edge.getTo(), visited, recStack)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }
    public List<List<String>> connectedComponents() {
        Set<String> visited = new HashSet<>();
        List<List<String>> components = new ArrayList<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfsForComponent(node, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void dfsForComponent(String node, Set<String> visited, List<String> component) {
        visited.add(node);
        component.add(node);

        for (UndirectedGraph.Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(edge.getTo())) {
                dfsForComponent(edge.getTo(), visited, component);
            }
        }
    }

}
