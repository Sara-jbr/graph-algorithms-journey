package org.graph.algorithms.advance;

import java.util.*;

public class EdmondsKarp {
    private final int V;
    private final int[][] capacity;
    private final List<List<Integer>> adj;

    public EdmondsKarp(int V) {
        this.V = V;
        capacity = new int[V][V];
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int from, int to, int cap) {
        capacity[from][to] = cap;
        adj.get(from).add(to);
        adj.get(to).add(from); // Add reverse path for residual graph
    }

    public int maxFlow(int source, int sink) {
        int flow = 0;
        int[] parent = new int[V];

        while (bfs(source, sink, parent)) {
            // Find minimum capacity in the path
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            // Update residual capacities
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }

            flow += pathFlow;
        }
        return flow;
    }

    private boolean bfs(int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        parent[source] = -2;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (parent[v] == -1 && capacity[u][v] > 0) {
                    parent[v] = u;
                    if (v == sink) return true;
                    queue.offer(v);
                }
            }
        }
        return false;
    }
}

