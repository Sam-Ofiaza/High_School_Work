import java.util.Arrays;

public class QuickSort
{
    public static void main(String[] args){
        int[] test = {9,4,1,3,9,6,6,1,2};
        System.out.println("original array: " + Arrays.toString(test));
        System.out.println("sorted 1 list of length 10\nnlogn = " + (10*Math.log10(10)) + "\nn^2 = " + Math.pow(10,2));
        quickSort(test, 0, test.length-1);
        System.out.println("sorted array: " + Arrays.toString(test));
        System.out.println("comparisons: " + quickSort(test,0,test.length-1) + "\n");
    }
    public static int quickSort(int arr[], int start, int end){
        int comparisons = 0;
        if(start < end){
            int[] vals = partition(arr, start, end);
            int mid = vals[0];
            comparisons += vals[1] + quickSort(arr, start, mid-1) + quickSort(arr, mid+1, end);
            //System.out.println("quicksorting [" + start + ", " + (mid-1) + "]");
            //System.out.println("quicksorting [" + (mid+1) + ", " + end + "]");
            return comparisons;
        }
        return comparisons;
    }
    public static int[] partition(int[] arr, int start, int end){
        int comp = 0;
        int pivot = arr[end];
        //System.out.println("pivot: " + pivot);
        int i = end-1;
        int j = start;
        //System.out.println("i = " + i + "\nj = " + j);
        while(i-j > 1){
            if((i-j > 1) && arr[j] < pivot){
                j++;
                comp++;
                //System.out.println("j = " + j);
            }
            if((i-j > 1) && arr[i] > pivot){
                i--;
                comp++;
                //System.out.println("i = " + i);
            }
            if(arr[j] > pivot && arr[i] < pivot){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                comp++;
                //System.out.println("swapping " + arr[i] + " (at " + j + ") and " + arr[j] + " (at " + i + ")");
                //System.out.println(Arrays.toString(arr));
            }
        }
        if(arr[j] > pivot){
            int temp = arr[end];
            arr[end] = arr[j];
            arr[j] = temp;
            comp++;
        }
        else if(arr[i] > pivot){
            int temp = arr[end];
            arr[end] = arr[i];
            arr[i] = temp;
            comp++;
        }
        //System.out.println("swapping " + arr[i] + " and " + arr[end]);
        //System.out.println(Arrays.toString(arr));

        int[] results = {i, comp};
        return results;
    }
}