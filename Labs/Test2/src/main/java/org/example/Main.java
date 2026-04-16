package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Square s1 = new Square(4); // Тільки сторона
        System.out.println(s1.getArea()); // 16
        System.out.println(s1.getDiagonal()); // 4√2 ≈ 5.656

        Square s2 = new Square(4, 5.656854249, 2, 2.828427125, 16, 16, 8); // З усіма параметрами
        System.out.println(s2.getArea()); // 16
        System.out.println(s2.getSideFromDiagonal()); // 4
        System.out.println(s2.getDiagonalTriangleArea()); // 8

        Square square = new Square(5);
        System.out.println("Area: " + square.getArea());
        System.out.println("Perimeter: " + square.getPerimeter());
    }
}

