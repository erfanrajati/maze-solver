import java.util.*;

public class MazeGraph 
{
    private class Vertex
    {
        int i, j = 0;
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
    

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    int[][] mazeMatrix = {{}};

    public MazeGraph(int height, int width) 
    {
        try (Scanner inputMatrix = new Scanner(System.in)) 
        {
            for (int i = 0; i < height; i++) 
            {
                for (int j = 0; j < width; j++)
                {
                    mazeMatrix[i][j] = inputMatrix.nextInt();
                }
            }
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
