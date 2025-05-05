package org.graph.algorithms.basic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private Map<String, List<Edge>> graph = new HashMap<>();

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public void addEdge(String from, String to, int weight) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new Edge(from, to, weight));

    }

    public static class Edge {
        private String from;
        private String to;
        private int weight;

        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to + " : " + weight;
        }
    }
}
