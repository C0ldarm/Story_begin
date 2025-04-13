package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RomToInt converter = new RomToInt();

        System.out.println("Welcome to Roman Numeral to Integer Converter!");
        System.out.println("Enter a Roman numeral (e.g., IV, MMXXI) or type 'exit', press space, or leave empty to quit:");

        while (true) {
            System.out.print("Enter a Roman numeral: ");
            String input = scanner.nextLine().trim();

            // Перевірка на умови завершення
            if (input.equalsIgnoreCase("exit") || input.isEmpty() || input.equals(" ")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // Конвертація римських цифр у число
                int number = converter.convert(input);

                // Виведення результату
                System.out.println("Integer: " + number);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}