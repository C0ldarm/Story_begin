package com.example.geometry;

import java.util.Objects;

/**
 * Клас для роботи з геометричною фігурою "Трикутник".
 */
public class Triangle {

    // Додано: константа EPSILON
    private static final double EPSILON = 1e-9;

    // Змінено: поля зроблені private final (Immutable)
    private final double sideA;
    private final double sideB;
    private final double sideC;

    // Змінено: додано валідацію в конструктор
    public Triangle(double sideA, double sideB, double sideC) {
        validateSides(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    // Додано функцію isValidTriangle
    public static boolean isValidTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 &&
               (a + b > c + EPSILON) &&
               (a + c > b + EPSILON) &&
               (b + c > a + EPSILON);
    }

    // Додано функцію validateSides
    private static void validateSides(double a, double b, double c) {
        if (!isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException(
                String.format("Неможливо створити трикутник зі сторонами: a=%.2f, b=%.2f, c=%.2f", a, b, c)
            );
        }
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    public double getArea() {
        double semiPerimeter = getPerimeter() / 2.0;
        return Math.sqrt(
            semiPerimeter *
            (semiPerimeter - sideA) *
            (semiPerimeter - sideB) *
            (semiPerimeter - sideC)
        );
    }

    // Змінено: порівняння double через EPSILON
    public boolean isRightAngled() {
        double a2 = sideA * sideA;
        double b2 = sideB * sideB;
        double c2 = sideC * sideC;

        return Math.abs(a2 + b2 - c2) < EPSILON ||
               Math.abs(a2 + c2 - b2) < EPSILON ||
               Math.abs(b2 + c2 - a2) < EPSILON;
    }

    // Змінено: порівняння double через EPSILON
    public boolean isIsosceles() {
        return Math.abs(sideA - sideB) < EPSILON ||
               Math.abs(sideA - sideC) < EPSILON ||
               Math.abs(sideB - sideC) < EPSILON;
    }

    // Змінено: порівняння double через EPSILON
    public boolean isEquilateral() {
        return Math.abs(sideA - sideB) < EPSILON &&
               Math.abs(sideB - sideC) < EPSILON;
    }

    // Додано функцію equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Math.abs(triangle.sideA - sideA) < EPSILON &&
               Math.abs(triangle.sideB - sideB) < EPSILON &&
               Math.abs(triangle.sideC - sideC) < EPSILON;
    }

    // Додано функцію hashCode
    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    // Додано функцію toString
    @Override
    public String toString() {
        return String.format("Triangle{sideA=%.2f, sideB=%.2f, sideC=%.2f}", sideA, sideB, sideC);
    }
}
