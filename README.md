# Graph Algorithms Journey

I’m currently working on a complete learning journey through graph algorithms, progressing from basic to advanced levels. The project is structured into three parts:

Basic Level: To understand graph structures and implement fundamental algorithms.

Intermediate Level: To explore various graph traversal techniques and solve common graph problems.

Advanced Level: To tackle optimization, networks, and complex graph analysis.

The project is organized in a clear directory structure and is written in Java, with a focus on clarity, modularity, and real-world relevance. By working through this, I’m gaining hands-on experience with graph algorithms that can be applied to solve real-world problems.

---

## What is a Graph?

A **graph** is a data structure made up of two main components:

- **Vertices (or Nodes)** → Represent entities like cities, people, or web pages.
- **Edges** → Represent connections between the vertices, like roads, friendships, or hyperlinks.

---

## 🔹 Simple Real-World Example of a Graph:

Let’s say we have 4 cities: A, B, C, D  
And the following roads between them:

```
A -- B
|    |
C -- D
```

In this graph:

- **Vertices**: A, B, C, D
- **Edges**: Connections like A–B or C–D

---

## Types of Graphs:

### 1. **Undirected Graph**

Edges have no direction. If A is connected to B, then B is also connected to A.

🔸 **Real-life example**:  
Friendship in a social network — if Ali is friends with Reza, Reza is friends with Ali.

---

### 2. **Directed Graph**

Edges have a direction. If A connects to B, it doesn’t mean B connects back to A.

🔸 **Real-life example**:  
Instagram — you can follow someone, but they may not follow you back.

---

### 3. **Weighted Graph**

Each edge has a **weight** — a number that represents distance, time, cost, or importance.

🔸 **What is Weight?**  
A weight indicates how costly or long it is to move from one node to another.

🔸 **Real-life example**:  
A road map with distances:  
If A to B is 5 km, and A to C is only 2 km, then:

So, going from A to C is faster or cheaper.

### Positive Weights

These are the most common and natural. The weights represent:

- Distance (e.g., 5 km between two cities)
- Time (e.g., 2 hours between two locations)
- Cost (e.g., $3 to go from station A to B)

**Example:**

A --5--> B

It means: going from A to B costs 5 units (distance, time, money, etc.)

### Negative Weights

A negative weight means going from one node to another gives a **benefit**, discount, or somehow reduces the total cost.

**Example:**

A -- -3 --> B

It means: going from A to B **reduces** total cost by 3.

### Why Negative Weights Matter

- Useful in **financial systems** (profit/loss graphs)
- Used in **Bellman-Ford algorithm** (it can handle negative weights)
- But **Dijkstra’s algorithm doesn’t work** with negative weights (it assumes all weights are positive)

### Negative Cycles

If a graph has a **cycle** with **total negative weight**, it means:

You could go in circles and **reduce the cost infinitely** (like an endless profit loop). That’s often a bug or a
critical issue in planning.

## Summary Table

| Graph Type | Simple Explanation          | Real-world Example             |
|------------|-----------------------------|--------------------------------|
| Undirected | Two-way connection          | Friendships in social networks |
| Directed   | One-way connection          | Instagram follows              |
| Weighted   | Edges have cost or distance | Road maps / GPS                |

---

## Why Are Graphs Important?

Graphs are powerful and widely used in real-world systems:

- **Navigation systems** (e.g., Google Maps)
- **Social networks** (e.g., Facebook, LinkedIn)
- **Web crawlers** (to index pages)
- **Game development & AI pathfinding**
- **Resource management in OS and databases**

---

## Algorithms Implemented (Basic)

🔹 **1. Depth-First Search (DFS)**  
**What it does**: Explores as far as possible down each branch before backtracking.

**Where it’s used**:
- Maze solving
- Topological Sorting
- Detecting cycles
- Searching for connected components

---

🔹 **2. Breadth-First Search (BFS)**  
**What it does**: Explores all neighbors at one depth before moving deeper.

**Where it’s used**:
- Finding the shortest path in unweighted graphs
- Peer-to-peer networks (e.g., torrent discovery)
- Web crawling

---

🔹 **3. Connected Components (in Undirected Graphs)**  
**What it does**: Identifies isolated groups (clusters) of connected nodes.

**Where it’s used**:
- Social network analysis
- Image processing
- Finding islands in a matrix

---

🔹 **4. Cycle Detection (in Undirected Graphs)**  
**What it does**: Checks if a cycle (loop) exists in the graph.

**Where it’s used**:
- Deadlock detection in operating systems
- Validating tree structures
- Compiler optimizations

## Summary Table

| Algorithm                    | Direction                                    | Weight Consideration                           |
|------------------------------|----------------------------------------------|------------------------------------------------|
| **DFS (Depth-First Search)**  | Both (works on directed and undirected graphs) | No (does not consider weight)                 |
| **BFS (Breadth-First Search)**| Both (works on directed and undirected graphs) | No (works on unweighted graphs)               |
| **Connected Components**      | Undirected only                             | No (focuses on connectivity, not weight)      |
| **Cycle Detection**           | Directed & Undirected                       | No (detects cycle presence, not weight)       |

---

These algorithms, such as **DFS**, **BFS**, **Connected Components**, and **Cycle Detection**, mainly focus on the structure of the graph and its connectivity, and they don't take edge weights into account.

If you need to account for edge weights, algorithms like **Dijkstra** (for finding the shortest path in weighted graphs) or **Bellman-Ford** (which can handle graphs with negative weights) would be more appropriate.

---