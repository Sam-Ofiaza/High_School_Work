import java.util.Arrays;

public class MergeSort
{
    public static void main(String[] args){
        //int[] test = {9,4,1,3,9,6,6,1,2};
        int[] test = makeTest(10);
        System.out.println("original array: " + Arrays.toString(test));
        System.out.println("sorted 1 list of length 10\nnlogn = " + (10*Math.log10(10)));
        System.out.println("comparisons: " + mergeSort(test,0,test.length-1) + "\n");

        test = makeTest(100);
        System.out.println("sorted 1 list of length 100\nnlogn = " + (100*Math.log10(100)));
        System.out.println("comparisons: " + mergeSort(test,0,test.length-1));
    }
    public static int[] makeTest(int size){
        int[] list = new int[size];
        for(int i = 0; i < size; i++)
            list[i] = (int)(Math.random() * size)+1;
        return list;
    }
    public static int mergeSort(int[] arr, int start, int end){
        int comparisons = 0;
        if(start < end){
            //System.out.println("list [" + start + ", " + end + "] goes into next level of recursion");
            int mid = (start+end)/2;
            //System.out.println("split list into [" + start + ", " + mid + "] and [" + (mid+1) + ", " + end + "]");
            comparisons += mergeSort(arr,start,mid) + mergeSort(arr,mid+1,end);
            return comparisons + merge(arr,start,mid,end);
        }
        return comparisons;
    }
    public static int merge(int[] arr, int start, int middle, int end){
        int comp = 0;
        for(int i = start+1; i <= end; i++){
            int current = arr[i];
            int j = i-1;
            if(j < 0 || arr[j] >= current)
                comp++;
            else
                while(j >= 0 && arr[j] > current){
                    arr[j+1] = arr[j];
                    j--;
                    comp++;
                }
            arr[j+1] = current;
        }
        //System.out.println("comparisons for merge of [" + start + ", " + middle + "] and [" + (middle+1) + ", " + end + "]: " + comp + "\n");
        return comp;
    }
}