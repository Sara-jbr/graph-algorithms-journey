package org.graph.algorithms.intermediate;


import org.graph.algorithms.basic.model.WeightedGraph;

import java.util.*;

public class KruskalMST {

    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private Map<String, Integer> rank = new HashMap<>();

        public String find(String node) {
            if (!parent.containsKey(node)) {
                parent.put(node, node);
                rank.put(node, 0);
            }
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node)));
            }
            return parent.get(node);
        }

        public void union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);

            if (root1.equals(root2)) return;

            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }



    public static List<WeightedGraph.Edge> kruskal(List<WeightedGraph.Edge> edges, List<String> nodes) {
        UnionFind uf = new UnionFind();
        List<WeightedGraph.Edge> mst = new ArrayList<>();
        Collections.sort(edges, Comparator.comparingInt(WeightedGraph.Edge::getWeight));  // Sort edges by weight

        for (WeightedGraph.Edge edge : edges) {
            if (!uf.find(edge.getFrom()).equals(uf.find(edge.getTo()))) {
                mst.add(edge);
                uf.union(edge.getFrom(), edge.getTo());
            }
        }

        return mst;
    }

}
