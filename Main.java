import java.util.Scanner;

public class Main 
{
    int[][][] vertexGuide = {
        {
            {1, 1, 1},
            {1, 0, 0},
            {1, 0, 1}
        }
    };

    Scanner userIn = new Scanner(System.in);
    int mazeHeight = userIn.nextInt();
    int mazeWidth = userIn.nextInt();
    MazeGraph maze = new MazeGraph(mazeHeight, mazeWidth);


    public void main(String[] args) 
    {
        // System.out.println("Hello, World!");
        System.out.println();
    }
}

