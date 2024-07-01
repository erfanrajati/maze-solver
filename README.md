# Maze Solver

## Problem Description

The Maze Solver project aims to solve a given maze represented by a matrix of `1`s and `0`s, where `1` represents a wall and `0` represents a path. The objective is to convert this matrix into a graph, find the shortest path from the start point to the end point, and provide a complete traversal of the maze.

## Solution Approach

### Steps to Solve the Problem

1. **Input Parsing**:
   - Read the maze dimensions and matrix from the input.
   - Identify the start point (fixed at `(1, 0)`) and the end point (last `0` in the second-to-last row).

2. **Graph Construction**:
   - Convert the maze matrix into a graph where each open cell (`0`) is a vertex.
   - Add edges between adjacent open cells to represent possible moves in the maze.

3. **Breadth-First Search (BFS)**:
   - Use BFS to traverse the graph from the start point.
   - Track the parent of each vertex to reconstruct the shortest path.

4. **Path Reconstruction**:
   - After BFS, use the parent map to reconstruct and print the shortest path from the start point to the end point.

### Algorithms Used

- **Breadth-First Search (BFS)**:
  BFS is employed to explore the maze and construct the graph. It ensures that the shortest path in terms of the number of edges is found from the start vertex to all other vertices.

## Code Explanation

### MazeGraph.java

This file contains the core logic for constructing the graph from the maze matrix and performing BFS to find the shortest path.

- **Vertex Class**: Represents a cell in the maze.
- **Edge Class**: Represents a connection between two vertices (adjacent cells).
- **MazeGraph Class**:
  - `buildGraphBFS()`: Constructs the graph using BFS.
  - `getGraphData()`: Prints all vertices and edges.
  - `printShortestPath()`: Prints the shortest path from the start to the end vertex.

### Main.java

This file contains the entry point of the program, handling user input and invoking the necessary methods from the `MazeGraph` class to solve the maze.

## Future Enhancements

1. **Multiple End Points**:
   - Extend the logic to handle multiple potential end points and find the nearest one.

2. **Graphical Visualization**:
   - Implement a graphical interface to visualize the maze, the graph, and the shortest path.

3. **Different Maze Sizes and Complexity**:
   - Test the algorithm with larger and more complex mazes to evaluate performance and optimize accordingly.

4. **Alternative Algorithms**:
   - Implement and compare other pathfinding algorithms such as A* or Dijkstra's algorithm to find the shortest path based on different criteria (e.g., weighted paths).

5. **Real-World Applications**:
   - Adapt the maze-solving logic for real-world applications such as robotic pathfinding, game development, or navigation systems.
