package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.DirectedGraph;

import java.util.*;

public class TopologicalSortBFS {

    public static List<String> sort(DirectedGraph graph) {
        Map<String, List<DirectedGraph.Edge>> adjList = graph.getGraph();

        // Step 1: Calculate in-degree
        Map<String, Integer> inDegree = new HashMap<>();
        for (String node : adjList.keySet()) {
            inDegree.putIfAbsent(node, 0);
            for (DirectedGraph.Edge edge : adjList.get(node)) {
                inDegree.put(edge.getTo(), inDegree.getOrDefault(edge.getTo(), 0) + 1);
            }
        }

        // Step 2: Queue for nodes with in-degree 0
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<String> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            String node = queue.poll();
            sorted.add(node);

            for (DirectedGraph.Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
                String neighbor = edge.getTo();
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 3: Check for cycle
        if (sorted.size() != inDegree.size()) {
            throw new RuntimeException("Graph has a cycle. Topological sort not possible.");
        }

        return sorted;
    }
}
