import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort
{
    public static void main(String[] args){
        int[] test = makeTest(10);
        System.out.println("original array: " + Arrays.toString(test));
        int[] values = runTest(test, 1);
        System.out.println("comparisons: " + values[3] + "\n");

        System.out.println("sorted array: " + Arrays.toString(test));
        values = runTest(test, 1);
        System.out.println("comparisons: " + values[3] + "\n");

        test = reverse(test);
        System.out.println("reverse array: " + Arrays.toString(test));
        values = runTest(test, 1);
        System.out.println("comparisons: " + values[3] + "\n");

        System.out.println("\nsorting 10 lists of length 10");
        test = makeTest(10);
        values = runTest(test, 10);
        System.out.println("best: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2] + "\ncomparisons: " + values[3]);
        System.out.println("n^2 = " + (int)Math.pow(10,2));

        System.out.println("\nsorting 10 lists of length 100");
        test = makeTest(100);
        values = runTest(test, 10);
        System.out.println("best: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2] + "\ncomparisons: " + values[3]);
        System.out.println("n^2 = " + (int)Math.pow(100,2));

        System.out.println("\nsorting 10 lists of length 1,000");
        test = makeTest(1000);
        values = runTest(test, 10);
        System.out.println("best: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2] + "\ncomparisons: " + values[3]);
        System.out.println("n^2 = " + (int)Math.pow(1000,2));

        System.out.println("\nsorting 10 lists of length 10,000");
        test = makeTest(10000);
        values = runTest(test, 10);
        System.out.println("best: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2] + "\ncomparisons: " + values[3]);
        System.out.println("n^2 = " + (int)Math.pow(10000,2));
        
        /*System.out.println("\nsorting 10 lists of length 100,000");
        test = makeTest(100000);
        values = runTest(test, 10);
        System.out.println("best: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2] + "\ncomparisons: " + values[3]);
        System.out.println("n^2 = " + (int)Math.pow(100000,2));*/

    }
    public static int[] makeTest(int size){
        int[] list = new int[size];
        for(int i = 0; i < size; i++)
            list[i] = (int)(Math.random() * size)+1;
        return list;
    }
    public static int[] reverse(int[] arr){
        int[] rev = new int[arr.length];
        int x = 0;
        for(int i = arr.length-1; i >= 0; i--){
            rev[x] = arr[i];
            x++;
        }
        return rev;
    }
    public static int[] runTest(int[] arr, int trials){
        int best = Integer.MAX_VALUE;
        int average = 0;
        int worst = Integer.MIN_VALUE;
        int comparisons = 0;
        for(int t = 0; t < trials; t++){
            int iterations = 0;
            for(int i = 1; i < arr.length; i++){
                int current = arr[i];
                int j = i-1;
                if(j < 0 || arr[j] <= current)
                    comparisons++;
                else
                    while(j >= 0 && arr[j] > current){
                        arr[j+1] = arr[j];
                        j--;
                        comparisons++;
                        iterations++;
                    }
                arr[j+1] = current;
            }
            if(best > iterations)
                best = iterations;
            else if(worst < iterations)
                worst = iterations;
            average += iterations;
            if(t < trials-1)
                for(int v = 0; v < arr.length; v++)
                    arr[v] = (int)(Math.random() * arr.length)+1;
        }

        average /= trials;
        int[] results = {best+1, average, worst+1, comparisons};
        return results;
    }
}