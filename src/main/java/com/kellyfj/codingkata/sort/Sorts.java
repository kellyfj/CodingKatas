package com.kellyfj.codingkata.sort;


/**
 *  |-----------|----------|----------|----------|---------|----------|
 *  | SORT      | BEST     | AVERAGE  | WORST    | SPACE   | IN PLACE | 
 *  |-----------|----------|----------|----------|---------|----------|
 *  | Bubble    | O(n)     | O(n^2)   | O(n^2)   | O(1)    |   YES    |
 *  | Selection | O(n^2)   | O(n^2)   | O(n^2)   | O(1)    |   YES    | 
 *  | Quick     | O(nlogn) | O(nlogn) | O(n^2)   | O(logn) |   YES    | 
 *  | Merge     | O(nlogn) | O(nlogn) | O(nlogn) | Depends |   NO     |
 *  | Heap      | O(nlogn) | O(nlogn) | O(nlogn) | O(1)    |   YES    |
 *  |-----------|----------|----------|----------|---------|----------|
 * 
 * @author kellyfj
 */
public class Sorts {

    public static int bubbleSort(int[] input) {
        // int[] returnArray = new int[input.length];
        int numSwaps = Integer.MAX_VALUE;
        int numPasses = 0;
        while (numSwaps > 0) {
            numSwaps = 0;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i + 1]) {
                    int temp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temp;
                    numSwaps++;
                }
            }
            numPasses++;
        }

        return numPasses;
    }

    public static void selectionSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min])
                    min = j;
            }

            // swap elements
            int temp = input[min];
            input[min] = input[i];
            input[i] = temp;

        }
    }

    private static int[] numbers;
    private static int[] tempArray;

    /**
     * Largely lifted from
     * http://www.vogella.com/articles/JavaAlgorithmsMergesort/article.html
     * 
     * @param input
     */
    public static void mergeSort(int[] input) {
        numbers = input;
        int size = input.length;
        tempArray = new int[size];
        mergesort(0, size - 1);
    }

    private static void mergesort(int low, int high) {
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private static void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            tempArray[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (tempArray[i] <= tempArray[j]) {
                numbers[k] = tempArray[i];
                i++;
            } else {
                numbers[k] = tempArray[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = tempArray[i];
            k++;
            i++;
        }

    }

    /************************
     * Quick Sort
     */

    public static void quickSort(int[] input) {
        quickSort(input, 0, input.length - 1);
    }

    private static void quickSort(int[] input, int low, int high) {
        int pivot;
        if (high > low) {
            pivot = partition(input, low, high);
            quickSort(input, low, pivot - 1);
            quickSort(input, pivot + 1, high);
        }

    }

    private static int partition(int[] input, int low, int high) {

        int left, right, pivotItem = input[low];
        left = low;
        right = high;
        while (left < right) {
            while (input[left] <= pivotItem && left < input.length - 2)
                left++;
            while (input[right] > pivotItem)
                right--;

            if (left < right) {
                // swap(input, left, right);
                int temp = input[left];
                input[left] = input[right];
                input[right] = temp;
            }
        }
        input[low] = input[right];
        input[right] = pivotItem;
        return right;

    }
}
