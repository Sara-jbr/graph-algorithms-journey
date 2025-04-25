package org.graph.algorithms.basic.model;

import java.util.*;

public class UnweightedGraph {
    private final Map<String, List<Edge>> graph = new HashMap<>();

    public void addEdge(String from, String to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to));
        graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from));
    }

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public static class Edge {
        private final String to;

        public Edge(String to) {
            this.to = to;
        }

        public String getTo() {
            return to;
        }
    }
}
