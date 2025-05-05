package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.*;

public class DijkstraAlgorithm {

    public static Map<String, Integer> dijkstra(WeightedGraph graph, String start) {
        Map<String, List<WeightedGraph.Edge>> adjList = graph.getGraph();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        // Step 1: Initialize distances
        for (String node : adjList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);  // Set all distances to infinity
        }
        distances.put(start, 0);  // Distance to the start node is 0

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String current = currentNode.name;

            for (WeightedGraph.Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
                String neighbor = edge.getTo();
                int currentDist = distances.getOrDefault(current, Integer.MAX_VALUE);
                int newDist = currentDist + edge.getWeight();

                if (newDist < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    pq.add(new Node(neighbor, newDist));
                }
            }

        }

        return distances;
    }

    public static List<String> getPath(Map<String, String> predecessors, String end) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Helper class to represent a node in the priority queue
    static class Node {
        String name;
        int distance;

        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }
}

