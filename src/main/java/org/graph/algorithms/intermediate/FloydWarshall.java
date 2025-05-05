package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {

    public static Map<String, Map<String, Integer>> shortestPaths(WeightedGraph graph) {
        Map<String, Map<String, Integer>> dist = new HashMap<>();

        // Initialize the distance matrix
        for (String vertex : graph.getGraph().keySet()) {
            dist.put(vertex, new HashMap<>());
            for (String vertex2 : graph.getGraph().keySet()) {
                if (vertex.equals(vertex2)) {
                    dist.get(vertex).put(vertex2, 0);
                } else {
                    dist.get(vertex).put(vertex2, Integer.MAX_VALUE);
                }
            }
        }

        // Set the initial edge weights
        for (String u : graph.getGraph().keySet()) {
            for (WeightedGraph.Edge edge : graph.getGraph().get(u)) {
                dist.get(u).put(edge.getTo(), edge.getWeight());
            }
        }

        // Floyd-Warshall algorithm
        for (String k : graph.getGraph().keySet()) {
            for (String i : graph.getGraph().keySet()) {
                for (String j : graph.getGraph().keySet()) {
                    if (dist.get(i).get(k) != Integer.MAX_VALUE && dist.get(k).get(j) != Integer.MAX_VALUE) {
                        int newDist = dist.get(i).get(k) + dist.get(k).get(j);
                        if (newDist < dist.get(i).get(j)) {
                            dist.get(i).put(j, newDist);
                        }
                    }
                }
            }
        }

        return dist;
    }
}