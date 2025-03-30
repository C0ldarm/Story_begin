package org.example;

public class Square {
    private double side;
    private Double diagonal;
    private Double inscribedCircleRadius;
    private Double circumscribedCircleRadius;
    private Double area;
    private Double perimeter;
    private Double diagonalTriangleArea;

    public Square(double side) {
        if (side <= 0) throw new IllegalArgumentException("Side must be positive");
        this.side = side;
        this.diagonal = null;
        this.inscribedCircleRadius = null;
        this.circumscribedCircleRadius = null;
        this.area = null;
        this.perimeter = null;
        this.diagonalTriangleArea = null;
    }

    public Square(double side, double diagonal, double inscribedCircleRadius, double circumscribedCircleRadius,
                  double area, double perimeter, double diagonalTriangleArea) {
        if (side <= 0 || diagonal <= 0 || inscribedCircleRadius <= 0 || circumscribedCircleRadius <= 0 ||
                area <= 0 || perimeter <= 0 || diagonalTriangleArea <= 0)
            throw new IllegalArgumentException("All parameters must be positive");
        this.side = side;
        this.diagonal = diagonal;
        this.inscribedCircleRadius = inscribedCircleRadius;
        this.circumscribedCircleRadius = circumscribedCircleRadius;
        this.area = area;
        this.perimeter = perimeter;
        this.diagonalTriangleArea = diagonalTriangleArea;
    }

    // 1. Отримати сторону
    public double getSide() { return side; }

    // 2. Обчислити площу
    public double getArea() { return area != null ? area : side * side; }

    // 3. Обчислити периметр
    public double getPerimeter() { return perimeter != null ? perimeter : 4 * side; }

    // 4. Обчислити діагональ
    public double getDiagonal() { return diagonal != null ? diagonal : side * Math.sqrt(2); }

    // 5. Обчислити радіус вписаного кола
    public double getInscribedCircleRadius() { return inscribedCircleRadius != null ? inscribedCircleRadius : side / 2; }

    // 6. Обчислити радіус описаного кола
    public double getCircumscribedCircleRadius() { return circumscribedCircleRadius != null ? circumscribedCircleRadius : getDiagonal() / 2; }

    // 7. Обчислити площу вписаного кола
    public double getInscribedCircleArea() { return Math.PI * Math.pow(getInscribedCircleRadius(), 2); }

    // 8. Обчислити площу описаного кола
    public double getCircumscribedCircleArea() { return Math.PI * Math.pow(getCircumscribedCircleRadius(), 2); }

    // 9. Обчислити половину діагоналі
    public double getHalfDiagonal() { return getDiagonal() / 2; }

    // 10. Обчислити чверть периметра
    public double getQuarterPerimeter() { return getPerimeter() / 4; }

    // 11. Обчислити кут між сторонами
    public double getAngleDegrees() { return 90; }

    // 12. Обчислити суму всіх кутів
    public double getSumOfAngles() { return 4 * getAngleDegrees(); }

    // 13. Обчислити сторону через діагональ
    public double getSideFromDiagonal() { return getDiagonal() / Math.sqrt(2); }

    // 14. Обчислити площу через діагональ
    public double getAreaFromDiagonal() { return Math.pow(getSideFromDiagonal(), 2); }

    // 15. Обчислити периметр через діагональ
    public double getPerimeterFromDiagonal() { return 4 * getSideFromDiagonal(); }

    // 16. Обчислити сторону через радіус вписаного кола
    public double getSideFromInscribedCircleRadius() { return 2 * getInscribedCircleRadius(); }

    // 17. Обчислити площу через радіус вписаного кола
    public double getAreaFromInscribedCircleRadius() { return Math.pow(getSideFromInscribedCircleRadius(), 2); }

    // 18. Обчислити периметр через радіус вписаного кола
    public double getPerimeterFromInscribedCircleRadius() { return 4 * getSideFromInscribedCircleRadius(); }

    // 19. Обчислити діагональ через радіус вписаного кола
    public double getDiagonalFromInscribedCircleRadius() { return getSideFromInscribedCircleRadius() * Math.sqrt(2); }

    // 20. Обчислити сторону через радіус описаного кола
    public double getSideFromCircumscribedCircleRadius() { return 2 * getCircumscribedCircleRadius() / Math.sqrt(2); }

    // 21. Обчислити площу через радіус описаного кола
    public double getAreaFromCircumscribedCircleRadius() { return Math.pow(getSideFromCircumscribedCircleRadius(), 2); }

    // 22. Обчислити периметр через радіус описаного кола
    public double getPerimeterFromCircumscribedCircleRadius() { return 4 * getSideFromCircumscribedCircleRadius(); }

    // 23. Обчислити відношення площі до периметра
    public double getAreaToPerimeterRatio() { return getArea() / getPerimeter(); }

    // 24. Обчислити відношення діагоналі до сторони
    public double getDiagonalToSideRatio() { return getDiagonal() / side; }

    // 25. Обчислити відношення площі до діагоналі
    public double getAreaToDiagonalRatio() { return getArea() / getDiagonal(); }

    // 26. Обчислити довжину дуги вписаного кола
    public double getInscribedCircleCircumference() { return 2 * Math.PI * getInscribedCircleRadius(); }

    // 27. Обчислити довжину дуги описаного кола
    public double getCircumscribedCircleCircumference() { return 2 * Math.PI * getCircumscribedCircleRadius(); }

    // 28. Обчислити площу трикутника від діагоналі
    public double getDiagonalTriangleArea() { return diagonalTriangleArea != null ? diagonalTriangleArea : (side * side) / 2; }

    // 29. Обчислити периметр трикутника від діагоналі
    public double getDiagonalTrianglePerimeter() { return 2 * side + getDiagonal(); }

    // 30. Обчислити висоту трикутника від діагоналі
    public double getDiagonalTriangleHeight() { return side / Math.sqrt(2); }

    // 31. Обчислити кут між діагоналлю та стороною
    public double getDiagonalToSideAngleDegrees() { return 45; }

    // 32. Обчислити суму кутів трикутника від діагоналі
    public double getDiagonalTriangleAngleSum() { return 180; }

    // 33. Обчислити синус кута між діагоналлю та стороною
    public double getSineOfDiagonalAngle() { return Math.sin(Math.toRadians(45)); }

    // 34. Обчислити косинус кута між діагоналлю та стороною
    public double getCosineOfDiagonalAngle() { return Math.cos(Math.toRadians(45)); }

    // 35. Обчислити тангенс кута між діагоналлю та стороною
    public double getTangentOfDiagonalAngle() { return Math.tan(Math.toRadians(45)); }

    // 36. Обчислити площу чверті квадрата
    public double getQuarterArea() { return getArea() / 4; }

    // 37. Обчислити відношення вписаного до описаного радіуса
    public double getInscribedToCircumscribedRadiusRatio() { return getInscribedCircleRadius() / getCircumscribedCircleRadius(); }

    // 38. Обчислити різницю площ описаного та вписаного кіл
    public double getCircumscribedMinusInscribedCircleArea() { return getCircumscribedCircleArea() - getInscribedCircleArea(); }

    // 39. Обчислити суму площ вписаного та описаного кіл
    public double getSumOfCircleAreas() { return getInscribedCircleArea() + getCircumscribedCircleArea(); }

    // 40. Обчислити відношення діагоналі до вписаного радіуса
    public double getDiagonalToInscribedRadiusRatio() { return getDiagonal() / getInscribedCircleRadius(); }

    // 41. Обчислити площу кільця між описаним і вписаним колами
    public double getRingAreaBetweenCircles() { return getCircumscribedCircleArea() - getInscribedCircleArea(); }

    // 42. Обчислити довжину чверті дуги вписаного кола
    public double getQuarterInscribedCircleArcLength() { return getInscribedCircleCircumference() / 4; }

    // 43. Обчислити довжину чверті дуги описаного кола
    public double getQuarterCircumscribedCircleArcLength() { return getCircumscribedCircleCircumference() / 4; }

    // 44. Обчислити відношення периметра до вписаного радіуса
    public double getPerimeterToInscribedRadiusRatio() { return getPerimeter() / getInscribedCircleRadius(); }

    // 45. Обчислити відношення площі до вписаного радіуса
    public double getAreaToInscribedRadiusRatio() { return getArea() / getInscribedCircleRadius(); }

    // 46. Обчислити сторону через площу
    public double getSideFromArea() { return Math.sqrt(getArea()); }

    // 47. Обчислити сторону через периметр
    public double getSideFromPerimeter() { return getPerimeter() / 4; }

    // 48. Обчислити діагональ через площу
    public double getDiagonalFromArea() { return Math.sqrt(2 * getArea()); }

    // 49. Обчислити периметр через площу
    public double getPerimeterFromArea() { return 4 * Math.sqrt(getArea()); }

    // 50. Обчислити вписаний радіус через площу
    public double getInscribedRadiusFromArea() { return Math.sqrt(getArea()) / 2; }

    // 51. Обчислити описаний радіус через площу
    public double getCircumscribedRadiusFromArea() { return Math.sqrt(2 * getArea()) / 2; }

    // 52. Обчислити площу через периметр
    public double getAreaFromPerimeter() { return Math.pow(getPerimeter() / 4, 2); }

    // 53. Обчислити діагональ через периметр
    public double getDiagonalFromPerimeter() { return (getPerimeter() / 4) * Math.sqrt(2); }

    // 54. Обчислити відношення трикутника від діагоналі до площі квадрата
    public double getDiagonalTriangleToSquareAreaRatio() { return getDiagonalTriangleArea() / getArea(); }

    // 55. Обчислити відношення периметра до описаного радіуса
    public double getPerimeterToCircumscribedRadiusRatio() { return getPerimeter() / getCircumscribedCircleRadius(); }

    // 56. Обчислити відношення площі до описаного радіуса
    public double getAreaToCircumscribedRadiusRatio() { return getArea() / getCircumscribedCircleRadius(); }

    // 57. Обчислити суму периметра і діагоналі
    public double getPerimeterPlusDiagonal() { return getPerimeter() + getDiagonal(); }

    // 58. Обчислити різницю периметра і діагоналі
    public double getPerimeterMinusDiagonal() { return getPerimeter() - getDiagonal(); }

    // 59. Обчислити відношення дуги вписаного кола до периметра
    public double getInscribedCircleCircumferenceToPerimeterRatio() { return getInscribedCircleCircumference() / getPerimeter(); }

    // 60. Обчислити відношення дуги описаного кола до периметра
    public double getCircumscribedCircleCircumferenceToPerimeterRatio() { return getCircumscribedCircleCircumference() / getPerimeter(); }

    // 61. Обчислити площу сектора вписаного кола (90°)
    public double getInscribedCircleSectorArea() { return (Math.PI * Math.pow(getInscribedCircleRadius(), 2)) / 4; }

    // 62. Обчислити площу сектора описаного кола (90°)
    public double getCircumscribedCircleSectorArea() { return (Math.PI * Math.pow(getCircumscribedCircleRadius(), 2)) / 4; }

    // 63. Обчислити відношення площі трикутника до вписаного кола
    public double getDiagonalTriangleToInscribedCircleAreaRatio() { return getDiagonalTriangleArea() / getInscribedCircleArea(); }

    // 64. Обчислити відношення площі трикутника до описаного кола
    public double getDiagonalTriangleToCircumscribedCircleAreaRatio() { return getDiagonalTriangleArea() / getCircumscribedCircleArea(); }

    // 65. Обчислити відношення суми кутів трикутника до суми кутів квадрата
    public double getDiagonalTriangleAngleSumToSquareAngleSumRatio() { return getDiagonalTriangleAngleSum() / getSumOfAngles(); }
}