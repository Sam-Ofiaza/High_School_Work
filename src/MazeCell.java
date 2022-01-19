public class MazeCell
{
    private int row;
    private int col;
    private boolean blocked;
    private boolean beenHere;
    private boolean backTracked;
    private boolean start;
    private boolean end;

    public MazeCell(int r, int c, boolean bl, boolean be, boolean ba, boolean s, boolean e){
        row = r;
        col = c;
        blocked = bl;
        beenHere = be;
        backTracked = ba;
        start = s;
        end = e;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public boolean getBlocked(){
        return blocked;
    }
    public void setBeenHere(boolean h){
        beenHere = h;
    }
    public boolean getBeenHere(){
        return beenHere;
    }
    public void setBackTracked(boolean t){
        backTracked = t;
    }
    public boolean getBackTracked(){
        return backTracked;
    }
    public boolean getStart(){
        return start;
    }
    public boolean getEnd(){
        return end;
    }
    public String toString(){
        if(backTracked)
            return "X";
        else if(beenHere)
            return "-";
        else if(blocked)
            return "1";
        return "0";
    }
}