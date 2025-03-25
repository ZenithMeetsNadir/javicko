package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        int[] arr = {11100, -55050, 84940, 848480, 45, 88, -9874, 67676, 55555, 10, 1, 0, -9};
        int[] arr3 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        for (int i : filterOdd(arr)) {
            System.out.printf("%d; ", i);
        }
        System.out.println();
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

    private interface IOddLambda {
        void process(int i);
    }

    private static void foreachOdd(int[] array, IOddLambda lambda) {
        for (int i : array) {
            if (i % 2 != 0)
                lambda.process(i);
        }
    }

    private static int[] filterOdd(int[] array) {
        AtomicInteger oddCount = new AtomicInteger();
        foreachOdd(array, (i) -> oddCount.getAndIncrement());

        int[] result = new int[oddCount.get()];

        AtomicInteger j = new AtomicInteger();
        foreachOdd(array, (i) -> result[j.getAndIncrement()] = i);

        return result;
    }

    private static int factorial(int n) {
        if (n == 0)
            return 1;

        return n * factorial(n - 1);
    }
    
    private static int fibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int powerUp(int base, int power) {
        if (power == 0)
            return 1;

        return base * powerUp(base, power - 1);
    }
}