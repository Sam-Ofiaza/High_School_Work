import java.util.Arrays;

public class BinarySearch
{
    public static void main(String[] args){
        int[] test = makeTest(10);
        int[] values = runTest(test, 10);

        System.out.println("binary search:");

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

    }
    public static int[] makeTest(int size){
        int[] list = new int[size];
        for(int i = 0; i < size; i++)
            list[i] = (int)(Math.random() * size)+1;
        for(int i = 0; i < size-1; i++){
            int lowest = i;
            for(int j = i+1; j < size; j++)
                if(list[j] < list[lowest])
                    lowest = j;
            int temp = list[i];
            list[i] = list[lowest];
            list[lowest] = temp;
        }
        return list;
    }
    public static int[] runTest(int[] arr, int trials){
        int best = Integer.MAX_VALUE;
        int average = 0;
        int worst = Integer.MIN_VALUE;

        for(int i = 0; i < trials; i++){
            int x = (int)(Math.random() * arr.length)+1;
            int iterations = 0;
            int start = 0;
            int end = arr.length;
            int middle = start+end/2;
            while(start <= end && middle < arr.length){
                if(x == arr[middle]){
                    if(best > iterations)
                        best = iterations;
                    else if(worst < iterations)
                        worst = iterations;
                    start = end+1;
                }
                else if(x > arr[middle])
                    start = middle+1;
                else if(x < arr[middle])
                    end = middle-1;
                middle = (start+end)/2;
                iterations++;
            }
            average += iterations;
            arr = makeTest(arr.length);
        }
        average /= trials;
        int[] results = {best+1, average, worst+1};
        return results;
    }
}