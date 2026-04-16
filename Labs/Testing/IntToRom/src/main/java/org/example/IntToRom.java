package org.example;

public class IntToRom {
    private static final int MAX_ROMAN_VALUE = 3999;
    private static final int[][] ROMAN_VALUES = {
            {1000, 'M'},
            {900, 'C', 'M'},
            {500, 'D'},
            {400, 'C', 'D'},
            {100, 'C'},
            {90, 'X', 'C'},
            {50, 'L'},
            {40, 'X', 'L'},
            {10, 'X'},
            {9, 'I', 'X'},
            {5, 'V'},
            {4, 'I', 'V'},
            {1, 'I'}
    };

    public String convert(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive and greater than 0");
        }
        if (number > MAX_ROMAN_VALUE) {
            throw new IllegalArgumentException("Number exceeds maximum Roman numeral value of " + MAX_ROMAN_VALUE);
        }

        StringBuilder roman = new StringBuilder();
        int remaining = number;

        for (int[] romanValue : ROMAN_VALUES) {
            int value = romanValue[0];
            while (remaining >= value) {
                if (romanValue.length == 2) {
                    roman.append((char) romanValue[1]);
                } else {
                    roman.append((char) romanValue[1]).append((char) romanValue[2]);
                }
                remaining -= value;
            }
        }

        return roman.toString();
    }
}