import java.util.Stack;
import java.awt.Point;
import java.util.Scanner;

public class MazeRunner
{
    public static void main(String[] args){
        Maze maze1 = new Maze("basicMaze.dat");
        System.out.println(maze1 + "\n");
        maze1.solveMaze();
        System.out.println(maze1 + "\n");
        System.out.println(maze1.getStackSolution() + "\n\n");

        maze1 = new Maze("maze3.dat");
        System.out.println(maze1 + "\n");
        maze1.solveMaze();
        System.out.println(maze1 + "\n");
        System.out.println(maze1.getStackSolution() + "\n\n");

        maze1 = new Maze("bigmaze5.dat");
        System.out.println(maze1 + "\n");
        maze1.solveMaze();
        System.out.println(maze1 + "\n");
        System.out.println(maze1.getStackSolution() + "\n\n");
    }
}