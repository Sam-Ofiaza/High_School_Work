import java.util.ArrayList;
import java.util.HashMap;

public class TowersRunner
{
    public static void main(String[] args){
        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> B = new ArrayList<Integer>();
        ArrayList<Integer> C = new ArrayList<Integer>();
        for(int i = 4; i >= 1; i--)
            A.add(i);

        HashMap map = new HashMap<String,ArrayList<Integer>>(); //Map for output
        map.put("A", A);
        map.put("B", B);
        map.put("C", C);

        solve(4, A, C, B, map);
        //System.out.println("A: " + A.toString() + "\nB: " + B.toString() + "\nC: " + C.toString());
    }
    public static void solve(int numRings, ArrayList<Integer> from, ArrayList<Integer> to, ArrayList<Integer> extra, HashMap<String,ArrayList<Integer>> m){
        if(numRings == 1){
            move(from, to);
            System.out.println(print(m));
        }
        else{
            solve(numRings-1, from, extra, to, m); // Take stack on top of this ring off
            move(from, to); // Move this ring
            System.out.println(print(m));
            solve(numRings-1, extra, to, from, m); // Put stack back on
        }
    }
    public static void move(ArrayList<Integer> from, ArrayList<Integer> to){
        int temp = from.get(from.size()-1);
        from.remove(from.get(from.size()-1));
        to.add(temp);
    }
    public static String print(HashMap<String,ArrayList<Integer>> m){ //For output
        int max = m.get("A").size() + m.get("B").size() + m.get("C").size();
        String[][] arr = new String[max+1][3];
        arr[max][0] = "A";
        arr[max][1] = "B";
        arr[max][2] = "C";
        for(int c = 0; c < 3; c++){
            int r = max-1;
            for(int i = 0; i < m.get(arr[max][c]).size(); i++){
                arr[r][c] = m.get(arr[max][c]).get(i) + "";
                r--;
            }
        }

        for(int r = 0; r < arr.length; r++){
            for(int c = 0; c < arr[r].length; c++){
                if(arr[r][c] == null)
                    arr[r][c] = "*";
            }
        }

        String output = "";
        for(int r = 0; r < arr.length; r++){
            for(int c = 0; c < arr[r].length; c++)
                output += arr[r][c] + " ";
            output += "\n";
        }
        return output;
    }
}
/*
0 1
1 2
2 3
3 A B C
  0 1 2

solve(5, A, C, B)
    solve(4, A, B, C)
        solve(3, A, C, B)
            solve(2, A, B, C)
                solve(1, A, C, B)
                    move(A, C) //1
                move(A, B) //2
                solve(1, C, B, A)
                    move(C, B) //1
            move(A, C) //3
            solve(2, B, C, A)
                solve(1, B, A, C)
                    move(B, A) //1
                move(B, C) //2
                solve(1, A, C, B)
                    move(A, C) //1
        move(A, B) //4
        solve(3, C, B, A)
    move(A, C) //5
    solve(4, B, C, A)
 */