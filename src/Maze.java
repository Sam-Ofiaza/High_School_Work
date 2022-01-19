import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Point;
import java.util.Scanner;

public class Maze
{
    MazeCell[][] maze;
    Stack<Point> path;
    public Maze(String name)
    {
        try(BufferedReader bReader = new BufferedReader(new FileReader(name))){
            Scanner dimensions = new Scanner(bReader.readLine());
            maze = new MazeCell[Integer.parseInt(dimensions.next())][Integer.parseInt(dimensions.next())];
            int c = 0;
            for(int r = 0; r < maze.length; r++){
                Scanner input = new Scanner(bReader.readLine());
                while(input.hasNext()){
                    if(r == 0 && c == 0)
                        maze[r][c] = new MazeCell(r, c, isBlocked(input.nextInt()), false, false, true, true);
                    else if(r == maze.length-1 && c == maze[r].length-1)
                        maze[r][c] = new MazeCell(r, c, isBlocked(input.nextInt()), false, false, false, true);
                    else
                        maze[r][c] = new MazeCell(r, c, isBlocked(input.nextInt()), false, false, false, false);
                    c++;
                }
                c = 0;
            }
            path = new Stack<Point>();
        }
        catch(IOException e){
            System.out.println("File reading exception.");
        }
    }
    public boolean isBlocked(int num){ //Is there a way other than a method to determine this?
        if(num == 0)
            return false;
        return true;
    }
    public void solveMaze(){
        //Find start
        for(int r = 0; r < maze.length; r++){ //To push start position
            for(int c = 0; c < maze[r].length; c++)
                if(maze[r][c].getStart())
                    path.push(new Point(maze[r][c].getRow(), maze[r][c].getCol()));
        }
        maze[0][0].setBeenHere(true);
        //Find end
        Point finish = new Point();
        for(int r = 0; r < maze.length; r++){ //To find end position
            for(int c = 0; c < maze[r].length; c++)
                if(maze[r][c].getEnd()){
                    finish.setLocation(r, c);
                }
        }
        //While loop to solve
        Point current = new Point(); //To hold current position
        while(!current.equals(finish)){ //Order: Right, Down, Left, Up
            current = new Point((int)(path.peek().getX()), (int)(path.peek().getY()));
            maze[(int)(current.getX())][(int)(current.getY())].setBeenHere(true);
            if( ((int)(current.getY())+1 < maze[(int)(current.getX())].length) && !(maze[(int)(current.getX())][(int)(current.getY())+1].getBlocked()) && !(maze[(int)(current.getX())][(int)(current.getY())+1 ].getBeenHere()) ){
                path.push( new Point((int)(current.getX()), (int)(current.getY())+1) );
            }//Right: c+1
            else if( ((int)(current.getX())+1 < maze.length) && !(maze[(int)(current.getX())+1][(int)(current.getY())].getBlocked()) && !(maze[(int)(current.getX())+1][(int)(current.getY())].getBeenHere()) ){
                path.push( new Point((int)(current.getX())+1, (int)(current.getY()) ) );
            }//Down: r+1
            else if( ((int)(current.getY())-1 > 0) && !(maze[(int)(current.getX())][(int)(current.getY())-1].getBlocked()) && !(maze[(int)(current.getX())][(int)(current.getY())-1].getBeenHere()) ){
                path.push( new Point((int)(current.getX()), (int)(current.getY())-1) );
            }//Left: c-1
            else if( ((int)(current.getX())-1 > 0) && !(maze[(int)(current.getX())-1][(int)(current.getY())].getBlocked()) && !(maze[(int)(current.getX())-1][(int)(current.getY())].getBeenHere()) ){
                path.push( new Point((int)(current.getX())-1, (int)(current.getY()) ) );
            }//Up: r-1
            else{
                maze[(int)(current.getX())][(int)(current.getY())].setBackTracked(true);
                path.pop();
            }
            //System.out.println(this);
        }
    }
    public String getStackSolution(){
        //Find end
        Point finish = new Point();
        for(int r = 0; r < maze.length; r++){ //To find end position
            for(int c = 0; c < maze[r].length; c++)
                if(maze[r][c].getEnd()){
                    finish.setLocation(r, c);
                }
        }
        //Print stack
        String toBeReversed = "";
        Point foo = new Point();
        while(!path.isEmpty()){
            foo = path.pop();
            if(foo.equals(finish))
                toBeReversed += "(" + (int)foo.getX() + "," + (int)foo.getY() + ")";
            else
                toBeReversed += "(" + (int)foo.getX() + "," + (int)foo.getY() + ") ";
        }
        Scanner input = new Scanner(toBeReversed);
        String output = "";
        int num = 0; //To know when to add a \n
        while(input.hasNext()){
            if(num >= 9){
                output = "\n" + input.next() + output;
                num = 0;
            }
            else{
                output = input.next() + output;
                num++;
            }
        }
        return output;
    }
    public String toString(){
        String str = "";
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++)
                str += maze[i][j] + " ";
            str += "\n";
        }
        return str;
    }
}