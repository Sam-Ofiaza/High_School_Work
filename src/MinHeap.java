import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;

public class MinHeap
{
    public static void main(String[] args){
        //create heap
        int[] empty = createHeap();
        System.out.println("blank heap: " + printArray(empty));

        //heapify
        int[] test = new int[5];
        for(int i = 0; i < test.length; i++)
            test[i] = (int)(Math.random() * 99) + 1;
        System.out.println("\noriginal: " + printArray(test));
        int[] test2 = truncateArray(heapify(test));
        System.out.println("\nheapified: " + printArray(test2));

        //findMin
        System.out.println("\nfindMin = " + findMin(test2));

        //insert
        System.out.println("\ninserting a 1");
        System.out.println("\nheap before insertion: " + printArray(test2));
        test2 = truncateArray(insert(test2, 1));
        System.out.println("\nheap after insertion method: " + printArray(test2));

        //extractMin
        System.out.println("\nextracting the min");
        System.out.println("\nheap before extraction: " + printArray(test2));
        int minimum = extractMin(test2);
        test2 = truncateArray(test2);
        System.out.println("\nextractMin = " + minimum + "\n\nheap after extraction method: " + printArray(test2));

        //deleteMin
        System.out.println("\ndeleting min");
        deleteMin(test2);
        test2 = truncateArray(test2);
        System.out.println("\nheap after deletion method: " + printArray(test2));

        //merge
        int[] test3 = new int[5];
        for(int i = 0; i < test3.length; i++)
            test3[i] = (int)(Math.random() * 99) + 1;
        int[] test4 = truncateArray(heapify(test3));
        System.out.println("\nmerging two heaps:\n" + printArray(test2));
        System.out.println("\n" + printArray(test4));
        int[] test5 = merge(test2, test4);
        System.out.println("\nheap after merge: " + printArray(test5));

        //meld
        int[] test6 = new int[5];
        for(int i = 0; i < test6.length; i++)
            test6[i] = (int)(Math.random() * 99) + 1;
        int[] test7 = truncateArray(heapify(test6));
        System.out.println("\nmelding two heaps:\n" + printArray(test5));
        System.out.println("\n" + printArray(test7));
        int[] test8 = meld(test5, test7);
        System.out.println("\nheap after meld: " + printArray(test8));

        //size
        System.out.println("\nsize of previous heap = " + size(test8));

        //isEmpty
        boolean blank = isEmpty(test8);
        if(blank)
            System.out.println("\nprevious heap is empty");
        else
            System.out.println("\nprevious heap is not empty");

        System.out.println("\nempty heap: " + printArray(empty));
        boolean blank2 = isEmpty(empty);
        if(blank2)
            System.out.println("\nprevious heap is empty");
        else
            System.out.println("\nprevious heap is not empty");
    }

    //basic
    public static int findMin(int[] heap){
        //always first root in min heaps
        return heap[0];
    }
    public static int[] insert(int[] heap, int val){ //add
        //place new val at leaf and siftUp
        if(heap.length == 0 || isEmpty(heap)){ //if empty 
            int[] heap2 = {val};
            return heap2;
        }
        else if(val == 0)
            return heap;
        HashSet<Integer> setty = new HashSet<Integer>(); //check if duplicate
        for(Integer x : heap)
            setty.add(x);
        if(setty.add(val)){ //returns true if val added (no existing dupes)
            boolean gap = false; //to store if gap found after loop
            int child = 0; //child index
            int[] heap2 = new int[heap.length+1]; //extra variable if original heap has no extra space to add val to
            for(int i = 0; i < heap.length; i++) //check for gaps
                if(heap[i] == 0 && !gap){ //first gap found
                    gap = true; //stop loop
                    heap[i] = val; //gap set to val
                    child = i; //child set to val index
                    heap2 = heap; //extra space not needed, extra variable overridden
                }
            if(!gap && heap[heap.length-1] == 0){ //no gap found and empty space available
                heap[heap.length-1] = val; //empty space set to val
                child = heap.length-1; //child set to val index
                heap2 = heap; //extra space not needed, extra variable overridden
            }
            else if(!gap && heap[heap.length-1] != 0){ //no gap found and no extra space
                for(int i = 0; i < heap.length; i++) //copy all original vals
                    heap2[i] = heap[i];
                heap2[heap2.length-1] = val; //empty space set to val
                child = heap2.length-1; //child set to val index
            }
            siftUp(heap2, child);
            return heap2;
        }
        else{
            return heap;
        }
    }
    public static int extractMin(int[] heap){
        //save min, delete from heap, return min
        if(heap.length == 0)
            return 0;
        int min = heap[0];
        deleteMin(heap);
        return min;
    }
    public static void deleteMin(int[] heap){
        //find last non-empty space, swap first and last vals, set last val 0, siftDown first val
        //method doesn't need a return value b/c changing array length not needed (except truncation only for aesthetic)
        if(heap.length == 1)
            heap[0] = 0;
        else if(heap.length >= 2){ //will stop cases of length 0
            int lastItem = 0;
            for(int i = heap.length-1; i >= 0; i--)
                if(heap[i] != 0){
                    lastItem = i;
                    i = -1;
                }

            //swap first and last vals
            int temp = heap[lastItem];
            heap[0] = temp;
            //set last val to 0
            heap[lastItem] = 0;

            siftDown(heap, 0);
        }
    }

    //creation
    public static int[] createHeap(){ //empty heap
        return new int[10];
    }
    public static int[] heapify(int[] vals){ //new heap from vals
        //add and sift up every value
        if(vals.length <= 1)
            return vals;
        int[] heap = {vals[0]};
        for(Integer x : vals)
            heap = insert(heap, x);
        return heap;
    }
    public static int[] merge(int[] heap1, int[] heap2){ //join two heaps, preserve originals, completely new heap
        //copy heap1 into new array, then add and sift up every value of heap2
        int[] heap3 = new int[heap1.length];
        for(int i = 0; i < heap1.length; i++)
            heap3[i] = heap1[i];
        for(Integer x : heap2)
            heap3 = insert(heap3, x);
        heap3 = truncateArray(heap3);
        return heap3;
    }
    public static int[] meld(int[] heap1, int[] heap2){ //join two heaps, destroy originals, in-place merge
        //copy heap1 into a new array with extra spaces, add and sift up vals of heap2
        for(Integer x : heap2)
            heap1 = insert(heap1, x);
        heap1 = truncateArray(heap1);
        return heap1;
    }

    //inspection
    public static int size(int[] heap){ //num items
        return heap.length;
    }
    public static boolean isEmpty(int[] heap){
        if(heap.length == 0)
            return true;
        for(int i = 0; i < heap.length; i++)
            if(heap[i] != 0)
                return false;
        return true;
    }

    //internal
    //all odd spots are left children, all even spots are right children
    public static void swap(int[] heap, int key1, int key2){ //replaces increaseKey/decreaseKey
        //must check if keys exist
        int temp = heap[key1];
        heap[key1] = heap[key2];
        heap[key2] = temp;
    }
    public static void siftUp(int[] heap, int child){  //update child to go up until smaller parent reached
        int parent = 0;
        int otherChild = 0;
        if(child%2 == 1){
            parent = (child-1)/2;
            otherChild = (parent*2)+2;
        }
        else{
            parent = (child-2)/2;
            otherChild = (parent*2)+1;
        }

        if((child < heap.length && parent >= 0 && heap[parent] != 0) && heap[child] != 0 && heap[child] < heap[parent]){
            swap(heap, child, parent);
            siftUp(heap, parent);
        }
        if((otherChild < heap.length && parent >= 0 && heap[otherChild] != 0 && heap[parent] != 0) && (heap[otherChild] < heap[parent])){
            swap(heap, otherChild, parent);
            siftUp(heap, parent);
        }

    }
    public static void siftDown(int[] heap, int parent){  //update parent to go down until bigger child reached
        int leftChild = (parent*2)+1;
        int rightChild = (parent*2)+2;

        if((leftChild < heap.length && heap[leftChild] != 0) && heap[parent] > heap[leftChild]){
            swap(heap, parent, leftChild);
            siftDown(heap, leftChild);
            siftDown(heap, rightChild);
        }
        if((rightChild < heap.length && heap[rightChild] != 0) && heap[parent] > heap[rightChild]){
            swap(heap, parent, rightChild);
            siftDown(heap, leftChild);
            siftDown(heap, rightChild);
        }

    }

    //Parent position = n
    //Left child = 2n + 1
    //Right child = 2n + 2
    public static String printArray(int[] arr){
        String str = "[";
        for(int i = 0; i < arr.length; i++)
            if(i == arr.length-1)
                str += arr[i] + "]";
            else
                str += arr[i] + ", ";
        return str;
    }
    public static int[] truncateArray(int[] arr){
        int[] truncatedArr = Arrays.copyOfRange(arr, 0, arr.length);
        //System.out.println("copied array: " + printArray(truncatedArr));
        int firstItem = 0; //front truncation
        for(int i = 0; i < arr.length; i++)
            if(arr[i] != 0){
                firstItem = i;
                i = arr.length;
            }
        if(firstItem != 0)
            truncatedArr = Arrays.copyOfRange(truncatedArr, firstItem, arr.length);
        //System.out.println("arr after front truncation: " + printArray(truncatedArr));
        int lastItem = -1; //back truncation
        for(int i = arr.length-1; i >= 0; i--)
            if(arr[i] != 0){
                lastItem = i;
                i = -1;
            }
        if(lastItem != -1)
            truncatedArr = Arrays.copyOfRange(truncatedArr, 0, lastItem+1);
        //System.out.println("arr after back truncation: " + printArray(truncatedArr));
        return truncatedArr;
    }
}