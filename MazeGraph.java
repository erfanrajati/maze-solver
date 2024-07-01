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

        public String getEdge() {
            return "(" + v.i + ", " + v.j + "), (" + u.i + ", " + u.j + ")";
        }
    }

    private class Current {
        int[] pos = {1, 0};
        Direction face = Direction.RIGHT;
        int[] right = {0, -1};

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

    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();
    private final int height, width;
    private final int[][] mazeMatrix;

    public MazeGraph(int height, int width) {
        this.height = height;
        this.width = width;
        this.mazeMatrix = new int[height][width];

        try (Scanner inputMatrix = new Scanner(System.in)) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mazeMatrix[i][j] = inputMatrix.nextInt();
                }
            }
        }
    }

    public void rightWallTrace() {
        Current current = new Current();
        int[][] mazeMatrixMod = Arrays.stream(mazeMatrix).map(int[]::clone).toArray(int[][]::new);
        mazeMatrixMod[height - 2][width - 1] = 1;  // Temporarily block the exit point

        do {
            int nextI = current.pos[0] + current.right[0];
            int nextJ = current.pos[1] + current.right[1];

            if (mazeMatrixMod[nextI][nextJ] == 0) {
                current.turnRight();
                nextI = current.pos[0] + current.right[0];
                nextJ = current.pos[1] + current.right[1];
            }

            while (mazeMatrixMod[nextI][nextJ] == 1) {
                current.turnLeft();
                nextI = current.pos[0] + current.right[0];
                nextJ = current.pos[1] + current.right[1];
            }

            current.advance();
            Vertex v = new Vertex(current.pos[0], current.pos[1]);
            this.vertices.add(v); // Add vertex

            if (this.vertices.size() > 1) {
                Vertex u = vertices.get(vertices.size() - 2);
                Edge e = new Edge(v, u);
                edges.add(e); // Add edge
            }
        } while (!(current.pos[0] == 1 && current.pos[1] == 0));
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
