import java.util.Arrays;

public class LinearSearch
{
    public static void main(String[] args){
        int[] test = makeTest(10);
        int[] values = runTest(test, 10);

        System.out.println("linear search:");

        System.out.println("array: " + Arrays.toString(test) + "\nlength: 10" + "\ntrials: 10" + "\nbest: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2]);

        test = makeTest(100);
        values = runTest(test, 10);
        System.out.println("\narray: " + Arrays.toString(test) + "\nlength: 100" + "\ntrials: 10" + "\nbest: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2]);

        test = makeTest(1000);
        values = runTest(test, 10);
        System.out.println("\narray length: 1,000" + "\ntrials: 10" + "\nbest: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2]);

        test = makeTest(10000);
        values = runTest(test, 10);
        System.out.println("\narray length: 10,000" + "\ntrials: 10" + "\nbest: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2]);

        test = makeTest(100000);
        values = runTest(test, 10);
        System.out.println("\narray length: 10,000" + "\ntrials: 10" + "\nbest: " + values[0] + "\naverage: " + values[1] + "\nworst: " + values[2]);

    }
    public static int[] makeTest(int size){
        int[] list = new int[size];
        for(int i = 0; i < size; i++)
            list[i] = (int)(Math.random() * size)+1;
        return list;
    }
    public static int[] runTest(int[] arr, int trials){
        int best = Integer.MAX_VALUE;
        int average = 0;
        int worst = Integer.MIN_VALUE;
        for(int i = 0; i < trials; i++){
            int x = (int)(Math.random() * arr.length)+1;
            for(int j = 0; j < arr.length; j++){
                if(arr[j] == x){
                    if(best > j)
                        best = j;
                    else if(worst < j)
                        worst = j;
                    average += j;
                    j = arr.length;
                }
            }
            for(int v = 0; v < arr.length; v++)
                arr[v] = (int)(Math.random() * arr.length)+1;
        }
        average /= trials;
        int[] results = {best+1, average, worst+1};
        return results;
    }
}