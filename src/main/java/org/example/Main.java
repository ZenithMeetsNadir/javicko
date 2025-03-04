package org.example;

public class Main {

    public static void main(String[] args) {
        int[] arr = {11100, -55050, 84940, 848480, 45};

        System.out.printf("sum: %d\n\n", sumArrayEntries(arr));
        System.out.printf("largest: %d\n\n", largestElement(arr));
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
}