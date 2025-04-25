package org.graph.algorithms.basic.model;

import java.util.*;

public class WeightedGraph {
    private final Map<String, List<Edge>> graph = new HashMap<>();

    public void addEdge(String from, String to, int weight) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
    }

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public static class Edge {
        String to;
        int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public String getTo() {
            return to;
        }
    }
}
