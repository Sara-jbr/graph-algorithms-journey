package org.graph.algorithms.advance;

import java.util.*;

public class TarjanSCC {
    private final int V;
    private final List<List<Integer>> graph;
    private int time = 0;
    private final int[] ids;
    private final int[] low;
    private final boolean[] onStack;
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final List<List<Integer>> sccs = new ArrayList<>();

    public TarjanSCC(int V) {
        this.V = V;
        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        ids = new int[V];
        Arrays.fill(ids, -1);
        low = new int[V];
        onStack = new boolean[V];
    }

    public void addEdge(int from, int to) {
        graph.get(from).add(to);
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < V; i++) {
            if (ids[i] == -1) {
                dfs(i);
            }
        }
        return sccs;
    }

    private void dfs(int at) {
        ids[at] = low[at] = time++;
        stack.push(at);
        onStack[at] = true;

        for (int to : graph.get(at)) {
            if (ids[to] == -1) {
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
            } else if (onStack[to]) {
                low[at] = Math.min(low[at], ids[to]);
            }
        }

        // Start a new SCC
        if (ids[at] == low[at]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                scc.add(node);
                if (node == at) break;
            }
            sccs.add(scc);
        }
    }
}

