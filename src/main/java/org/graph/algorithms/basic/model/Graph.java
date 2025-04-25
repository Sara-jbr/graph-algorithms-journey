package org.graph.algorithms.basic.model;

import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addPerson(String person) {
        adjList.putIfAbsent(person, new ArrayList<>());
    }

    public void addConnection(String person1, String person2) {
        adjList.get(person1).add(person2);
        adjList.get(person2).add(person1);
    }

    public Map<String, List<String>> getAdjList() {
        return adjList;
    }

    public void printGraph() {
        for (String person : adjList.keySet()) {
            System.out.println(person + " is connected to: " + adjList.get(person));
        }
    }
}
