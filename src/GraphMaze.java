import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.awt.Point;

public class GraphMaze
{
    private HashMap<Point,ArrayList<Point>> map;
    private int shortest;
    private ArrayList<Point> path;
    private String[][] mazeCopy; //for printing purposes
    public GraphMaze(int[][] maze)
    {
        map = new HashMap<Point,ArrayList<Point>>();
        shortest = Integer.MAX_VALUE;
        path = new ArrayList<Point>();
        for(int r = 0; r < maze.length; r++)
            for(int c = 0; c < maze.length; c++)
                if(maze[r][c] == 0){
                    Point current = new Point(r, c);
                    if(r+1 < maze.length && maze[r+1][c] == 0){ //down
                        Point node = new Point(r+1, c);
                        if(!map.containsKey(current))
                            map.put(current, new ArrayList<Point>(Arrays.asList(node)));
                        else
                            map.get(current).add(node);
                    }
                    if(c+1 < maze[r].length && maze[r][c+1] == 0){ //right
                        Point node = new Point(r, c+1);
                        if(!map.containsKey(current))
                            map.put(current, new ArrayList<Point>(Arrays.asList(node)));
                        else
                            map.get(current).add(node);
                    }
                    if(c-1 >= 0 && maze[r][c-1] == 0){ //left
                        Point node = new Point(r, c-1);
                        if(!map.containsKey(current))
                            map.put(current, new ArrayList<Point>(Arrays.asList(node)));
                        else
                            map.get(current).add(node);
                    }
                    if(r-1 >= 0 && maze[r-1][c] == 0){ //up
                        Point node = new Point(r-1, c);
                        if(!map.containsKey(current))
                            map.put(current, new ArrayList<Point>(Arrays.asList(node)));
                        else
                            map.get(current).add(node);
                    }
                }
        //System.out.println(mapToString());
        mazeCopy = new String[maze.length][maze[0].length];
        for(int r = 0; r < maze.length; r++)
            for(int c = 0; c < maze[r].length; c++)
                mazeCopy[r][c] = maze[r][c] + "";
    }
    public void check(Point first, Point second, ArrayList<Point> placesUsed)
    {
        System.out.println("check(" + pointToString(first) + ", " + pointToString(second) + ", " + pointListToString(placesUsed) + ")");
        if(first.equals(second) && placesUsed.size() < shortest){
            //System.out.println("found match");
            placesUsed.add(first);
            shortest = placesUsed.size();
            path = placesUsed;
        }
        else if(placesUsed.size() > shortest)
            //System.out.println("path length exceeded shortest - short circuiting");
            System.out.print(""); //is there another way to do this?
        else{
            for(Point x : map.get(first))
                if(!placesUsed.contains(x)){
                    ArrayList<Point> values = new ArrayList<Point>();
                    for(Point y : placesUsed)
                        values.add(y);
                    values.add(first);
                    check(x, second, values);
                }
        }
    }
    public String pointToString(Point p){
        return "(" + (int)p.getX() + "," + (int)p.getY() + ")";
    }
    public String mapToString(){
        String str = "";
        for(Point key : map.keySet())
            str += pointToString(key) + " - " + pointListToString(map.get(key)) + "\n";
        return str;
    }
    public String pointListToString(ArrayList<Point> p){
        String str = "[";
        for(int i = 0; i < p.size(); i++){
            if(i == p.size()-1)
                str += pointToString(p.get(i));
            else
                str += pointToString(p.get(i)) + ", ";
        }
        return str + "]";
    }
    public String toString(Point f, Point s, ArrayList<Point> p){
        check(f, s, p);
        for(int i = 0; i < path.size(); i++)
            mazeCopy[(int)path.get(i).getX()][(int)path.get(i).getY()] = "-";

        String str = "";
        for(int i = 0; i < mazeCopy.length; i++){
            for(int j = 0; j < mazeCopy[i].length; j++)
                str += mazeCopy[i][j] + " ";
            str += "\n";
        }
        return str;
    }
}