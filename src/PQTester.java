import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PQTester
{
    private Queue<String> pQueue;
    private Queue<String> queue;
    private int numVals; //how many vals in String

    public PQTester()
    {
        setPQ("");
    }

    public PQTester(String list)
    {
        setPQ(list);
    }

    public void setPQ(String list1)
    {
        pQueue = new PriorityQueue<String>();
        Scanner input = new Scanner(list1);
        String value = "";
        numVals = 0;
        while(input.hasNext()){
            value = input.next();
            pQueue.add(value);
            numVals++;
        }

        queue = new LinkedList<String>();
        input = new Scanner(list1);
        value = "";
        while(input.hasNext()){
            value = input.next();
            queue.add(value);
        }
    }

    public String getMin()
    {
        return pQueue.peek();
    }

    public String getNaturalOrder()
    {
        String output = "";
        String remove = "";
        for(int i = 0; i < numVals-1; i++){
            remove = queue.remove();
            output += remove + " ";
        }
        return output;
    }

    public String toString()
    {
        String getMin = getMin();
        String ordered = "";
        for(int i = 0; i < numVals; i++)
            ordered += pQueue.poll() + " ";
        return "toString() " + ordered + "\ngetMin() " + getMin + "\ngetNaturalOrder() " + getNaturalOrder();
    }
}