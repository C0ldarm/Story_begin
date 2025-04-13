package org.example;

public class RomToInt {
    private static final int MAX_ROMAN_VALUE = 3999;

    public int convert(String roman) {
        if (roman == null || roman.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        roman = roman.trim().toUpperCase();
        if (!isValidRoman(roman)) {
            throw new IllegalArgumentException("Invalid Roman numeral format");
        }

        int result = 0;
        int i = 0;

        while (i < roman.length()) {
            if (i + 1 < roman.length()) {
                String twoChars = roman.substring(i, i + 2);
                if (isSubtractivePair(twoChars)) {
                    result += getValue(twoChars);
                    i += 2;
                    continue;
                }
            }

            char current = roman.charAt(i);
            result += getValue(current);
            i++;
        }

        if (result > MAX_ROMAN_VALUE) {
            throw new IllegalArgumentException("Roman numeral exceeds maximum value of " + MAX_ROMAN_VALUE);
        }
        if (result <= 0) {
            throw new IllegalArgumentException("Roman numeral must represent a positive number");
        }

        return result;
    }

    private boolean isValidRoman(String roman) {
        // Перевірка на валідні символи
        if (!roman.matches("^[IVXLCDM]+$")) {
            return false;
        }

        // Перевірка на невалідні повтори символів
        if (roman.contains("IIII") || roman.contains("XXXX") || roman.contains("CCCC") || roman.contains("MMMM")) {
            return false;
        }
        if (roman.contains("VV") || roman.contains("LL") || roman.contains("DD")) {
            return false;
        }

        // Перевірка на невалідні субтрактивні комбінації
        if (roman.contains("VX") || roman.contains("VL") || roman.contains("VC") || roman.contains("VD") ||
                roman.contains("VM") || roman.contains("LC") || roman.contains("LD") || roman.contains("LM") ||
                roman.contains("DM")) {
            return false;
        }

        // Перевірка порядку символів (символи мають бути у спадному порядку, окрім субтрактивних пар)
        for (int i = 0; i < roman.length() - 1; i++) {
            if (i + 1 < roman.length() && isSubtractivePair(roman.substring(i, i + 2))) {
                i++; // Пропускаємо субтрактивну пару
                continue;
            }
            char current = roman.charAt(i);
            char next = roman.charAt(i + 1);
            if (getValue(current) < getValue(next)) {
                return false; // Некоректний порядок (наприклад, IX є субтрактивною парою, але MI — ні)
            }
        }

        return true;
    }

    private boolean isSubtractivePair(String pair) {
        return pair.equals("IV") || pair.equals("IX") || pair.equals("XL") ||
                pair.equals("XC") || pair.equals("CD") || pair.equals("CM");
    }

    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Invalid Roman numeral character: " + c);
        }
    }

    private int getValue(String pair) {
        switch (pair) {
            case "IV": return 4;
            case "IX": return 9;
            case "XL": return 40;
            case "XC": return 90;
            case "CD": return 400;
            case "CM": return 900;
            default: throw new IllegalArgumentException("Invalid Roman numeral pair: " + pair);
    }
}