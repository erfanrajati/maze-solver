import java.util.*;

public class MazeGraph 
{
    ArrayList<int[]> edges = new ArrayList<>();
    ArrayList<Integer> vertices = new ArrayList<>();

    int[][] mazeMatrix = {{}};

    public MazeGraph(int height, int width) 
    {
        try (Scanner inputMatrix = new Scanner(System.in)) {
            for (int i = 0; i < height; i++) 
            {
                for (int j = 0; j < width; j++)
                {
                    mazeMatrix[i][j] = inputMatrix.nextInt();
                }
            }
        }
    }

    public void addVertex(int v) 
    {
        vertices.add(v);
    }

    public void addEdge(int v, int u) 
    {
        int[] edge = {v, u};
        edges.add(edge);
    }
}
