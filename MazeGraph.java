import java.util.*;

enum Direction {
    LEFT,
    DOWN,
    RIGHT,
    UP
}

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

    private class Current {
        int[] pos = {1, 0};
        Direction face = Direction.RIGHT;
        int[] right = {0, 1};

        public void advance() {
            switch (this.face) {
                case RIGHT -> this.pos[1]++;
                case DOWN -> this.pos[0]++;
                case LEFT -> this.pos[1]--;
                case UP -> this.pos[0]--;
            }
        }

        public void turnRight() {
            switch (this.face) {
                case RIGHT -> {
                    this.face = Direction.DOWN;
                    this.right = new int[]{1, 0};
                }
                case DOWN -> {
                    this.face = Direction.LEFT;
                    this.right = new int[]{0, -1};
                }
                case LEFT -> {
                    this.face = Direction.UP;
                    this.right = new int[]{-1, 0};
                }
                case UP -> {
                    this.face = Direction.RIGHT;
                    this.right = new int[]{0, 1};
                }
            }
        }

        public void turnLeft() {
            switch (this.face) {
                case RIGHT -> {
                    this.face = Direction.UP;
                    this.right = new int[]{-1, 0};
                }
                case DOWN -> {
                    this.face = Direction.RIGHT;
                    this.right = new int[]{0, 1};
                }
                case LEFT -> {
                    this.face = Direction.DOWN;
                    this.right = new int[]{1, 0};
                }
                case UP -> {
                    this.face = Direction.LEFT;
                    this.right = new int[]{0, -1};
                }
            }
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
                    int input = inputMatrix.nextInt();
                    mazeMatrix[i][j] = input;
                    System.out.println(input);
                    System.out.print(i);
                    System.out.print(" ");
                    System.out.print(j);
                    System.out.println(" Added");
                }
            }
        }
    }

    public void rightWallTrace() {
        Current current = new Current();
        int[][] mazeMatrixMod = Arrays.stream(mazeMatrix).map(int[]::clone).toArray(int[][]::new);
        mazeMatrixMod[height - 2][width - 1] = 1;  // Temporarily block the exit point

        // Ensure start position is valid
        if (mazeMatrix[1][0] == 1) {
            System.out.println("Invalid start position.");
            return;
        }

        Set<Vertex> visitedVertices = new HashSet<>();
        Vertex start = new Vertex(current.pos[0], current.pos[1]);
        vertices.add(start);
        visitedVertices.add(start);

        Vertex previousVertex = null;
        Direction previousDirection = null;
        int steps = 0; // To avoid infinite loop

        while (true) {
            int nextI = current.pos[0] + current.right[0];
            int nextJ = current.pos[1] + current.right[1];

            if (isInBounds(nextI, nextJ) && mazeMatrixMod[nextI][nextJ] == 0) {
                current.turnRight();
                nextI = current.pos[0] + current.right[0];
                nextJ = current.pos[1] + current.right[1];
            }

            while (!isInBounds(nextI, nextJ) || mazeMatrixMod[nextI][nextJ] == 1) {
                current.turnLeft();
                nextI = current.pos[0] + current.right[0];
                nextJ = current.pos[1] + current.right[1];
            }

            current.advance();
            Vertex v = new Vertex(current.pos[0], current.pos[1]);

            // Debugging output
            System.out.println("Current position: (" + current.pos[0] + ", " + current.pos[1] + "), direction: " + current.face);

            // Break if we're back to the start
            if (v.equals(start) && steps > 0) {
                break;
            }

            // Add the vertex if it's not been visited
            if (!visitedVertices.contains(v)) {
                visitedVertices.add(v);
                vertices.add(v);
            } else if (v.equals(previousVertex) && current.face == previousDirection) {
                break;
            }

            // Add the edge if it's not been added
            Vertex u = new Vertex(current.pos[0] - current.right[0], current.pos[1] - current.right[1]);
            Edge e = new Edge(v, u);
            if (!edges.contains(e)) {
                edges.add(e);
            }

            previousVertex = v;
            previousDirection = current.face;
            steps++;
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

    // public static void main(String[] args) {
    //     try (Scanner userIn = new Scanner(System.in)) {
    //         int mazeHeight = userIn.nextInt();
    //         int mazeWidth = userIn.nextInt();
    //         MazeGraph maze = new MazeGraph(mazeHeight, mazeWidth);
    //         maze.rightWallTrace(); // now the matrix should be converted into a graph.
    //         maze.getGraphData();
    //     }
    // }
}
