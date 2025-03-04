package org.example;

public final class CustomMath {
    public static double PI(long iterations) {
        double result = 0;

        for (long i = 1; i <= iterations; i++) {
            result += 4.0 / (i * 2 - 1) * (i % 2 == 1 ? 1 : -1);
        }

        return result;
    }

    public static long greatestFactor(long a, long b) {
        if (a == b)
            return a;
        else if (a > b)
            return greatestFactor(a - b, b);
        else
            return greatestFactor(a, b - a);
    }
}
