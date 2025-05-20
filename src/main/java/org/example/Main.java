package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        String[] testStrings = {
            "Martin",
            "nitraM",
            "+1234",
            "-1234",
            "1234.5678",
            "1234.5678.90",
            "-1234.5678.90"
        };

        for (String str : testStrings) {
            System.out.println("isName(\"" + str + "\") = " + isName(str));
            System.out.println("isUnsignedInteger(\"" + str + "\") = " + isUnsignedInteger(str));
            System.out.println("isInteger(\"" + str + "\") = " + isInteger(str));
            System.out.println("isDecimal(\"" + str + "\") = " + isDecimal(str));
            System.out.println();
        }
    }

    private static boolean isFirstUpperCase(String str) {
        if (str == null || str.isEmpty())
            return false;

        char firstChar = str.charAt(0);
        return Character.isUpperCase(firstChar);
    }

    private static boolean isName(String str) {
        if (!isFirstUpperCase(str))
            return false;

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isLowerCase(str.charAt(i)))
                return false;
        }

        return true;
    }

    private static boolean isDecimalNumberDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private static boolean isDecimal(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        char posSign = str.charAt(0);
        if (!(isDecimalNumberDigit(posSign) || posSign == '-' || posSign == '+'))
            return false;

        boolean hasDecimalPoint = false;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isDecimalNumberDigit(str.charAt(i))) {
                if (c == '.' && hasDecimalPoint)
                    return false;

                hasDecimalPoint = true;
            }
        }

        return true;
    }

    private static boolean isInteger(String str) {
        if (!isDecimal(str))
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.')
                return false;
        }

        return true;
    }

    private static boolean isUnsignedInteger(String str) {
        return isInteger(str) && str.charAt(0) != '-';
    }
}