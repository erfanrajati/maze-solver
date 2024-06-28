import java.util.*;

enum Direction
{
    LEFT,
    DOWN,
    RIGHT,
    UP
}


public class MazeGraph 
{
    private class Vertex
    {
        int i = 0;
        int j = 0;
        public Vertex(int i, int j)
        {
            this.i = i;
            this.j = j;
        }
    }

    private class Edge 
    {
        Vertex[] edge;
    
        public Edge(Vertex v, Vertex u) 
        {
            this.edge[0] = v;
            this.edge[1] = u;
        }
    }
    
    private class Current
    {
        int[] pose = {1, 0};
        Direction face;
        int[] right;

        public Current()
        {
            this.face = Direction.RIGHT;
            this.right[0] = 1;
            this.right[1] = 0;
        }

        public void advance() 
        {
            if (this.face == Direction.RIGHT) 
                this.pose[0] += 1;
            if (this.face == Direction.DOWN) 
                this.pose[1] += 1;
            if (this.face == Direction.LEFT) 
                this.pose[0] -= 1;
            if (this.face == Direction.UP) 
                this.pose[1] -= 1;
        }

        public void turnRight()
        {
            if (this.face == Direction.RIGHT) 
            {
                this.face = Direction.DOWN;
                this.right[0] = 0;
                this.right[1] = -1;
            }
            if (this.face == Direction.DOWN) 
            {
                this.face = Direction.LEFT;
                this.right[0] = -1;
                this.right[1] = 0;
            }
            if (this.face == Direction.LEFT) 
            {
                this.face = Direction.UP;
                this.right[0] = 0;
                this.right[1] = 1;
            }
            if (this.face == Direction.UP) 
            {
                this.face = Direction.RIGHT;
                this.right[0] = 1;
                this.right[1] = 0;
            }
        }
    }

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    int height = 0;
    int width = 0;
    int[][] mazeMatrix = {{}};

    public MazeGraph(int height, int width) 
    {
        this.height = height;
        this.width = width;
        try (Scanner inputMatrix = new Scanner(System.in)) 
        {
            for (int i = 0; i < height; i++) 
            {
                for (int j = 0; j < width; j++)
                    mazeMatrix[i][j] = inputMatrix.nextInt();
            }
        }
    }

    public void rightWallTrace()
    {
        Current current = new Current();
        int[] finish = {this.height - 2, this.width - 1};
        int right = mazeMatrix[current.pose[0] + current.right[0]][current.pose[1] + current.right[1]];
        int front = 0; // implement later

        while (current.pose != finish)
        {
            while (right == 1 && front != 1)
            {
                current.advance();
                right = mazeMatrix[current.pose[0] + current.right[0]][current.pose[1] + current.right[1]];
                Vertex v = new Vertex(current.pose[0], current.pose[1]);
                this.vertices.add(v);
                try 
                {
                    Vertex u = vertices.get(vertices.size() - 2);
                    Edge e = new Edge(v, u);
                    edges.add(e);
                }
                catch (Exception e) 
                {}

            }
            current.turnRight();
            right = mazeMatrix[current.pose[0] + current.right[0]][current.pose[1] + current.right[1]];
        }
    }

    // public void addVertex(int v) 
    // {
    //     vertices.add(v);
    // }

    // public void addEdge(int v, int u) 
    // {
    //     int[] edge = {v, u};
    //     edges.add(edge);
    // }
}
