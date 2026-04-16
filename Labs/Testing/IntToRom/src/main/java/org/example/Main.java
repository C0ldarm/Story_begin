package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntToRom converter = new IntToRom();

        System.out.println("Welcome to Integer to Roman Numeral Converter!");
        System.out.println("Enter an integer between 1 and 3999 (or type 'exit', press space, or leave empty to quit):");

        while (true) {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine().trim();
            // Перевірка на умови завершення
            if (input.equalsIgnoreCase("exit") || input.isEmpty() || input.equals(" ")) {
                System.out.println("Shutting down...");
                break;
            }

            try {
                // Конвертація введеного рядка в число
                int number = Integer.parseInt(input);

                // Конвертація числа в римські цифри
                String romanNumeral = converter.convert(number);

                // Виведення результату
                System.out.println("Roman numeral: " + romanNumeral);

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}