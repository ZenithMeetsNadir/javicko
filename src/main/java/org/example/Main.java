package org.example;

public class Main {

    public static void main(String[] args) {
        int[] arr = {11100, -55050, 84940, 848480, 45, 88, -9874, 67676, 55555, 10, 1, 0, -9};

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
}