package org.graph.algorithms.advance;


import java.util.*;

public class AStar {

    static class Node implements Comparable<Node> {
        String name;
        int g; // cost so far
        int f; // g + heuristic
        Node parent;

        Node(String name, int g, int f, Node parent) {
            this.name = name;
            this.g = g;
            this.f = f;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public static List<String> findPath(Map<String, List<String>> graph, String start, String goal, Map<String, Integer> heuristic) {
        PriorityQueue<Node> open = new PriorityQueue<>();
        Set<String> closed = new HashSet<>();

        open.add(new Node(start, 0, heuristic.getOrDefault(start, 0), null));

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current.name.equals(goal)) {
                return buildPath(current);
            }

            closed.add(current.name);

            for (String neighbor : graph.getOrDefault(current.name, Collections.emptyList())) {
                if (closed.contains(neighbor)) continue;

                int g = current.g + 1; // unweighted edge = cost 1
                int h = heuristic.getOrDefault(neighbor, 0);
                open.add(new Node(neighbor, g, g + h, current));
            }
        }

        return Collections.emptyList(); // no path
    }

    private static List<String> buildPath(Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(node.name);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}