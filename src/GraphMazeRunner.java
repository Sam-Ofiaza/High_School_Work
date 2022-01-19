import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import java.util.ArrayList;

public class GraphMazeRunner
{
    public static void main(String[] args){
        try(BufferedReader bReader = new BufferedReader(new FileReader("basicMaze.dat"))){
            Scanner dimensions = new Scanner(bReader.readLine());
            int[][] maze1 = new int[Integer.parseInt(dimensions.next())][Integer.parseInt(dimensions.next())];
            for(int r = 0; r < maze1.length; r++){
                Scanner input = new Scanner(bReader.readLine());
                for(int c = 0; c < maze1[r].length; c++)
                    maze1[r][c] = Integer.parseInt(input.next());
            }
            System.out.println(mazeToString(maze1));
            GraphMaze test1 = new GraphMaze(maze1);
            System.out.println(test1.toString(new Point(0,0), new Point(maze1.length-1,maze1[0].length-1), new ArrayList<Point>()) + "\n");
        }
        catch(IOException e){
            System.out.println("File reading exception.");
        }
        //test cases for maze3.dat and bigmaze5.dat, some bugs may appear
    }
    public static String mazeToString(int[][] m){
        String str = "";
        for(int r = 0; r < m.length; r++){
            for(int c = 0; c < m[0].length; c++)
                str += m[r][c] + " ";
            str += "\n";
        }
        return str;
    }
}