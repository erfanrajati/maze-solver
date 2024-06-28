import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        try (Scanner userIn = new Scanner(System.in)) {
            int mazeHeight = userIn.nextInt();
            int mazeWidth = userIn.nextInt();
            MazeGraph maze = new MazeGraph(mazeHeight, mazeWidth);
            maze.rightWallTrace(); // now the matrix should be converted into a graph.
            maze.getGraphData();
        }
    }
}

