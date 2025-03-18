package org.example;

public class Main {

    public static void main(String[] args) {
        int[] arr = {11100, -55050, 84940, 848480, 45, 88, -9874, 67676, 55555, 10, 1, 0, -9};
        int[] arr3 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(arr3);
        //sort(arr3);
        for (int i : arr3) {
            System.out.print(i + "; ");
        }
        System.out.println();

        for (int[] arrayPart : split(arr, 5)) {
            for (int i : arrayPart) {
                System.out.print(i + "; ");
            }

            System.out.println();
        }
    }

    private static int sumArrayEntries(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        return sum;
    }

    private static int largestElement(int[] array) {
        int largestCache = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > largestCache)
                largestCache = i;
        }
        return largestCache;
    }

    private static int[] reverseArray(int[] array) {
        int lastIndex = array.length - 1;
        int lastMiddle = array.length / 2 - 1;
        for (int i = 0; i <= lastMiddle; i++) {
            int temp = array[i];
            array[i] = array[lastIndex - i];
            array[lastIndex - i] = temp;
        }

        return array;
    }

    private static int[] reverseArrayClone(int[] array) {
        return reverseArray(array.clone());
    }

    private static int[][] split(int[] array, int rightStart) {
        int[][] result = new int[2][];
        result[0] = new int[rightStart];
        result[1] = new int[array.length - rightStart];

        System.arraycopy(array, 0, result[0], 0, rightStart);
        System.arraycopy(array, rightStart, result[1], 0, array.length - rightStart);

        return result;
    }

    private static void swap(int[] pair, int index) {
        int temp = pair[index];
        pair[index] = pair[index + 1];
        pair[index + 1] = temp;
    }

    private static void bubbleSort(int[] array) {
        boolean isSorted = false;
        int complexityPoints = 0;
        int n = 0;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - n - 1; i++) {
                complexityPoints++;
                if (array[i] > array[i + 1]) {
                    swap(array, i);
                    if (i > 0 && array[i - 1] > array[i])
                        isSorted = false;
                }
            }

            n++;
        }

        System.out.println("Complexity points: " + complexityPoints);
    }
}