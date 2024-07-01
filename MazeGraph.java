import java.util.*;

public class MazeGraph {

    private class Vertex {
        int i, j;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Vertex vertex = (Vertex) obj;
            return i == vertex.i && j == vertex.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        public String getVertex() {
            return i + " " + j;
        }
    }

    private class Edge {
        Vertex v, u;

        public Edge(Vertex v, Vertex u) {
            this.v = v;
            this.u = u;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge edge = (Edge) obj;
            return (Objects.equals(v, edge.v) && Objects.equals(u, edge.u)) ||
                    (Objects.equals(v, edge.u) && Objects.equals(u, edge.v));
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, u);
        }

        public String getEdge() {
            return "(" + v.i + ", " + v.j + "), (" + u.i + ", " + u.j + ")";
        }
    }

    private final Set<Vertex> vertices = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final int height, width;
    private final int[][] mazeMatrix;

    public MazeGraph(int height, int width) {
        this.height = height;
        this.width = width;
        this.mazeMatrix = new int[height][width];

        try (Scanner inputMatrix = new Scanner(System.in)) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (inputMatrix.hasNextInt()) {
                        mazeMatrix[i][j] = inputMatrix.nextInt();
                    } else {
                        System.out.println("Error: Unexpected input at (" + i + ", " + j + ")");
                        return;
                    }                
                }
            }
        }
    }

    public void buildGraphBFS() {
        boolean[][] visited = new boolean[height][width];
        Queue<Vertex> queue = new LinkedList<>();
        int[] start = {1, 0};

        if (mazeMatrix[start[0]][start[1]] == 1) {
            System.out.println("Invalid start position.");
            return;
        }

        Vertex startVertex = new Vertex(start[0], start[1]);
        queue.add(startVertex);
        visited[start[0]][start[1]] = true;
        vertices.add(startVertex);

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (int[] direction : directions) {
                int ni = current.i + direction[0];
                int nj = current.j + direction[1];

                if (isInBounds(ni, nj) && mazeMatrix[ni][nj] == 0 && !visited[ni][nj]) {
                    Vertex neighbor = new Vertex(ni, nj);
                    queue.add(neighbor);
                    visited[ni][nj] = true;
                    vertices.add(neighbor);

                    Edge edge = new Edge(current, neighbor);
                    edges.add(edge);
                }
            }
        }
    }

    private boolean isInBounds(int i, int j) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    public void getGraphData() {
        System.out.println("Vertices: ");
        for (Vertex v : vertices) {
            System.out.println(v.getVertex());
        }

        System.out.println();

        System.out.println("Edges: ");
        for (Edge e : edges) {
            System.out.println(e.getEdge());
        }
    }
}
