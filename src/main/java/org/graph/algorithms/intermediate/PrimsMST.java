package org.graph.algorithms.intermediate;

import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.*;

public class PrimsMST {

    public static List<WeightedGraph.Edge> prim(WeightedGraph graph, String start) {
        List<WeightedGraph.Edge> mst = new ArrayList<>();
        PriorityQueue<WeightedGraph.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(WeightedGraph.Edge::getWeight));
        Set<String> visited = new HashSet<>();
        visited.add(start);

        List<WeightedGraph.Edge> startEdges = graph.getGraph().get(start);
        if (startEdges != null) {
            pq.addAll(startEdges);
        }

        while (!pq.isEmpty()) {
            WeightedGraph.Edge edge = pq.poll();
            String toNode = edge.getTo();

            if (visited.contains(toNode)) continue;

            mst.add(edge);
            visited.add(toNode);

            List<WeightedGraph.Edge> adjacentEdges = graph.getGraph().get(toNode);
            if (adjacentEdges != null) {
                for (WeightedGraph.Edge adj : adjacentEdges) {
                    if (!visited.contains(adj.getTo())) {
                        pq.add(adj);
                    }
                }
            }
        }

        return mst;
    }
}
