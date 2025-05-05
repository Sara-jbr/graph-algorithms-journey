package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord {
    public static Map<String, Integer> shortestPaths(WeightedGraph graph, String source) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();

        // Initialize distances and predecessors
        for (String vertex : graph.getGraph().keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
        }
        distances.put(source, 0);

        // Relax edges (V-1) times
        for (int i = 0; i < graph.getGraph().size() - 1; i++) {
            for (String u : graph.getGraph().keySet()) {
                List<WeightedGraph.Edge> edges = graph.getGraph().get(u);
                for (WeightedGraph.Edge edge : edges) {
                    String v = edge.getTo();
                    int newDist = distances.get(u) + edge.getWeight();
                    if (newDist < distances.get(v)) {
                        distances.put(v, newDist);
                        predecessors.put(v, u);
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (String u : graph.getGraph().keySet()) {
            List<WeightedGraph.Edge> edges = graph.getGraph().get(u);
            for (WeightedGraph.Edge edge : edges) {
                String v = edge.getTo();
                if (distances.get(u) + edge.getWeight() < distances.get(v)) {
                    throw new RuntimeException("Graph contains a negative-weight cycle");
                }
            }
        }

        return distances;
    }
}