
import org.example.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    public void testGetSide() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getSide();

        // Then
        assertEquals(5, result, "Сторона має бути 5");
    }

    @Test
    public void testGetArea() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getArea();

        // Then
        assertEquals(16, result, "Площа має бути 16");
    }

    @Test
    public void testGetPerimeter() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getPerimeter();

        // Then
        assertEquals(24, result, "Периметр має бути 24");
    }

    @Test
    public void testGetDiagonal() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getDiagonal();

        // Then
        assertEquals(3 * Math.sqrt(2), result, 0.0001, "Діагональ має бути 3√2 ≈ 4.2426");
    }

    @Test
    public void testGetInscribedCircleRadius() {
        // Given
        Square s = new Square(8);

        // When
        double result = s.getInscribedCircleRadius();

        // Then
        assertEquals(4, result, "Радіус вписаного кола має бути 4");
    }

    @Test
    public void testGetCircumscribedCircleRadius() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getCircumscribedCircleRadius();

        // Then
        assertEquals(2 * Math.sqrt(2), result, 0.0001, "Радіус описаного кола має бути 2√2 ≈ 2.8284");
    }

    @Test
    public void testGetInscribedCircleArea() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getInscribedCircleArea();

        // Then
        assertEquals(Math.PI * 9, result, 0.0001, "Площа вписаного кола має бути π * 9 ≈ 28.2743");
    }

    @Test
    public void testGetCircumscribedCircleArea() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getCircumscribedCircleArea();

        // Then
        assertEquals(Math.PI * 2, result, 0.0001, "Площа описаного кола має бути π * 2 ≈ 6.2832");
    }

    @Test
    public void testGetHalfDiagonal() {
        // Given
        Square s = new Square(10);

        // When
        double result = s.getHalfDiagonal();

        // Then
        assertEquals(5 * Math.sqrt(2), result, 0.0001, "Половина діагоналі має бути 5√2 ≈ 7.0711");
    }

    @Test
    public void testGetQuarterPerimeter() {
        // Given
        Square s = new Square(8);

        // When
        double result = s.getQuarterPerimeter();

        // Then
        assertEquals(8, result, "Чверть периметра має бути 8");
    }

    @Test
    public void testGetAngleDegrees() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getAngleDegrees();

        // Then
        assertEquals(90, result, "Кут має бути 90°");
    }

    @Test
    public void testGetSumOfAngles() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getSumOfAngles();

        // Then
        assertEquals(360, result, "Сума кутів має бути 360°");
    }

    @Test
    public void testGetSideFromDiagonal() {
        // Given
        Square s = new Square(4, 5.656854249, 2, 2.828427125, 16, 16, 8);

        // When
        double result = s.getSideFromDiagonal();

        // Then
        assertEquals(4, result, 0.0001, "Сторона з діагоналі має бути 4");
    }

    @Test
    public void testGetAreaFromDiagonal() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getAreaFromDiagonal();

        // Then
        assertEquals(25, result, 0.0001, "Площа з діагоналі має бути 25");
    }

    @Test
    public void testGetPerimeterFromDiagonal() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getPerimeterFromDiagonal();

        // Then
        assertEquals(12, result, 0.0001, "Периметр з діагоналі має бути 12");
    }

    @Test
    public void testGetSideFromInscribedCircleRadius() {
        // Given
        Square s = new Square(6, 8.485281374, 3, 4.242640687, 36, 24, 18);

        // When
        double result = s.getSideFromInscribedCircleRadius();

        // Then
        assertEquals(6, result, 0.0001, "Сторона з вписаного радіуса має бути 6");
    }

    @Test
    public void testGetAreaFromInscribedCircleRadius() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getAreaFromInscribedCircleRadius();

        // Then
        assertEquals(16, result, 0.0001, "Площа з вписаного радіуса має бути 16");
    }

    @Test
    public void testGetPerimeterFromInscribedCircleRadius() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getPerimeterFromInscribedCircleRadius();

        // Then
        assertEquals(20, result, 0.0001, "Периметр з вписаного радіуса має бути 20");
    }

    @Test
    public void testGetDiagonalFromInscribedCircleRadius() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getDiagonalFromInscribedCircleRadius();

        // Then
        assertEquals(2 * Math.sqrt(2), result, 0.0001, "Діагональ з вписаного радіуса має бути 2√2 ≈ 2.8284");
    }

    @Test
    public void testGetSideFromCircumscribedCircleRadius() {
        // Given
        Square s = new Square(4, 5.656854249, 2, 2.828427125, 16, 16, 8);

        // When
        double result = s.getSideFromCircumscribedCircleRadius();

        // Then
        assertEquals(4, result, 0.0001, "Сторона з описаного радіуса має бути 4");
    }

    @Test
    public void testGetAreaFromCircumscribedCircleRadius() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getAreaFromCircumscribedCircleRadius();

        // Then
        assertEquals(36, result, 0.0001, "Площа з описаного радіуса має бути 36");
    }

    @Test
    public void testGetPerimeterFromCircumscribedCircleRadius() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getPerimeterFromCircumscribedCircleRadius();

        // Then
        assertEquals(12, result, 0.0001, "Периметр з описаного радіуса має бути 12");
    }

    @Test
    public void testGetAreaToPerimeterRatio() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getAreaToPerimeterRatio();

        // Then
        assertEquals(1.25, result, 0.0001, "Відношення площі до периметра має бути 1.25");
    }

    @Test
    public void testGetDiagonalToSideRatio() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getDiagonalToSideRatio();

        // Then
        assertEquals(Math.sqrt(2), result, 0.0001, "Відношення діагоналі до сторони має бути √2 ≈ 1.4142");
    }

    @Test
    public void testGetAreaToDiagonalRatio() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getAreaToDiagonalRatio();

        // Then
        assertEquals(3 / Math.sqrt(2), result, 0.0001, "Відношення площі до діагоналі має бути 3/√2 ≈ 2.1213");
    }

    @Test
    public void testGetInscribedCircleCircumference() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getInscribedCircleCircumference();

        // Then
        assertEquals(6 * Math.PI, result, 0.0001, "Довжина дуги вписаного кола має бути 6π ≈ 18.8496");
    }

    @Test
    public void testGetCircumscribedCircleCircumference() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getCircumscribedCircleCircumference();

        // Then
        assertEquals(4 * Math.sqrt(2) * Math.PI, result, 0.0001, "Довжина дуги описаного кола має бути 4√2π ≈ 17.7715");
    }

    @Test
    public void testGetDiagonalTriangleArea() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getDiagonalTriangleArea();

        // Then
        assertEquals(8, result, "Площа трикутника від діагоналі має бути 8");
    }

    @Test
    public void testGetDiagonalTrianglePerimeter() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getDiagonalTrianglePerimeter();

        // Then
        assertEquals(6 + 3 * Math.sqrt(2), result, 0.0001, "Периметр трикутника від діагоналі має бути 6 + 3√2 ≈ 10.2426");
    }

    @Test
    public void testGetDiagonalTriangleHeight() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getDiagonalTriangleHeight();

        // Then
        assertEquals(3 * Math.sqrt(2), result, 0.0001, "Висота трикутника від діагоналі має бути 3√2 ≈ 4.2426");
    }

    @Test
    public void testGetDiagonalToSideAngleDegrees() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getDiagonalToSideAngleDegrees();

        // Then
        assertEquals(45, result, "Кут між діагоналлю та стороною має бути 45°");
    }

    @Test
    public void testGetDiagonalTriangleAngleSum() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getDiagonalTriangleAngleSum();

        // Then
        assertEquals(180, result, "Сума кутів трикутника від діагоналі має бути 180°");
    }

    @Test
    public void testGetSineOfDiagonalAngle() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getSineOfDiagonalAngle();

        // Then
        assertEquals(Math.sqrt(2) / 2, result, 0.0001, "Синус 45° має бути √2/2 ≈ 0.7071");
    }

    @Test
    public void testGetCosineOfDiagonalAngle() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getCosineOfDiagonalAngle();

        // Then
        assertEquals(Math.sqrt(2) / 2, result, 0.0001, "Косинус 45° має бути √2/2 ≈ 0.7071");
    }

    @Test
    public void testGetTangentOfDiagonalAngle() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getTangentOfDiagonalAngle();

        // Then
        assertEquals(1, result, 0.0001, "Тангенс 45° має бути 1");
    }

    @Test
    public void testGetQuarterArea() {
        // Given
        Square s = new Square(8);

        // When
        double result = s.getQuarterArea();

        // Then
        assertEquals(16, result, "Чверть площі має бути 16");
    }

    @Test
    public void testGetInscribedToCircumscribedRadiusRatio() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getInscribedToCircumscribedRadiusRatio();

        // Then
        assertEquals(1 / Math.sqrt(2), result, 0.0001, "Відношення вписаного до описаного радіуса має бути 1/√2 ≈ 0.7071");
    }

    @Test
    public void testGetCircumscribedMinusInscribedCircleArea() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getCircumscribedMinusInscribedCircleArea();

        // Then
        assertEquals(Math.PI * 1, result, 0.0001, "Різниця площ кіл має бути π ≈ 3.1416");
    }

    @Test
    public void testGetSumOfCircleAreas() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getSumOfCircleAreas();

        // Then
        assertEquals(12 * Math.PI, result, 0.0001, "Сума площ кіл має бути 12π ≈ 37.6991");
    }

    @Test
    public void testGetDiagonalToInscribedRadiusRatio() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getDiagonalToInscribedRadiusRatio();

        // Then
        assertEquals(2 * Math.sqrt(2), result, 0.0001, "Відношення діагоналі до вписаного радіуса має бути 2√2 ≈ 2.8284");
    }

    @Test
    public void testGetRingAreaBetweenCircles() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getRingAreaBetweenCircles();

        // Then
        assertEquals(Math.PI * 1, result, 0.0001, "Площа кільця має бути π ≈ 3.1416");
    }

    @Test
    public void testGetQuarterInscribedCircleArcLength() {
        // Given
        Square s = new Square(8);

        // When
        double result = s.getQuarterInscribedCircleArcLength();

        // Then
        assertEquals(2 * Math.PI, result, 0.0001, "Чверть дуги вписаного кола має бути 2π ≈ 6.2832");
    }

    @Test
    public void testGetQuarterCircumscribedCircleArcLength() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getQuarterCircumscribedCircleArcLength();

        // Then
        assertEquals(Math.PI * Math.sqrt(2), result, 0.0001, "Чверть дуги описаного кола має бути √2π ≈ 4.4429");
    }

    @Test
    public void testGetPerimeterToInscribedRadiusRatio() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getPerimeterToInscribedRadiusRatio();

        // Then
        assertEquals(8, result, 0.0001, "Відношення периметра до вписаного радіуса має бути 8");
    }

    @Test
    public void testGetAreaToInscribedRadiusRatio() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getAreaToInscribedRadiusRatio();

        // Then
        assertEquals(6, result, 0.0001, "Відношення площі до вписаного радіуса має бути 6");
    }

    @Test
    public void testGetSideFromArea() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getSideFromArea();

        // Then
        assertEquals(4, result, 0.0001, "Сторона з площі має бути 4");
    }

    @Test
    public void testGetSideFromPerimeter() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getSideFromPerimeter();

        // Then
        assertEquals(6, result, 0.0001, "Сторона з периметра має бути 6");
    }

    @Test
    public void testGetDiagonalFromArea() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getDiagonalFromArea();

        // Then
        assertEquals(5 * Math.sqrt(2), result, 0.0001, "Діагональ з площі має бути 5√2 ≈ 7.0711");
    }

    @Test
    public void testGetPerimeterFromArea() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getPerimeterFromArea();

        // Then
        assertEquals(12, result, 0.0001, "Периметр з площі має бути 12");
    }

    @Test
    public void testGetInscribedRadiusFromArea() {
        // Given
        Square s = new Square(8);

        // When
        double result = s.getInscribedRadiusFromArea();

        // Then
        assertEquals(4, result, 0.0001, "Вписаний радіус з площі має бути 4");
    }

    @Test
    public void testGetCircumscribedRadiusFromArea() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getCircumscribedRadiusFromArea();

        // Then
        assertEquals(2 * Math.sqrt(2), result, 0.0001, "Описаний радіус з площі має бути 2√2 ≈ 2.8284");
    }

    @Test
    public void testGetAreaFromPerimeter() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getAreaFromPerimeter();

        // Then
        assertEquals(36, result, 0.0001, "Площа з периметра має бути 36");
    }

    @Test
    public void testGetDiagonalFromPerimeter() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getDiagonalFromPerimeter();

        // Then
        assertEquals(5 * Math.sqrt(2), result, 0.0001, "Діагональ з периметра має бути 5√2 ≈ 7.0711");
    }

    @Test
    public void testGetDiagonalTriangleToSquareAreaRatio() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getDiagonalTriangleToSquareAreaRatio();

        // Then
        assertEquals(0.5, result, 0.0001, "Відношення трикутника до площі має бути 0.5");
    }

    @Test
    public void testGetPerimeterToCircumscribedRadiusRatio() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getPerimeterToCircumscribedRadiusRatio();

        // Then
        assertEquals(4 * Math.sqrt(2), result, 0.0001, "Відношення периметра до описаного радіуса має бути 4√2 ≈ 5.6569");
    }

    @Test
    public void testGetAreaToCircumscribedRadiusRatio() {
        // Given
        Square s = new Square(3);

        // When
        double result = s.getAreaToCircumscribedRadiusRatio();

        // Then
        assertEquals(3 * Math.sqrt(2), result, 0.0001, "Відношення площі до описаного радіуса має бути 3√2 ≈ 4.2426");
    }

    @Test
    public void testGetPerimeterPlusDiagonal() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getPerimeterPlusDiagonal();

        // Then
        assertEquals(16 + 4 * Math.sqrt(2), result, 0.0001, "Сума периметра і діагоналі має бути 16 + 4√2 ≈ 21.6569");
    }

    @Test
    public void testGetPerimeterMinusDiagonal() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getPerimeterMinusDiagonal();

        // Then
        assertEquals(20 - 5 * Math.sqrt(2), result, 0.0001, "Різниця периметра і діагоналі має бути 20 - 5√2 ≈ 12.9289");
    }

    @Test
    public void testGetInscribedCircleCircumferenceToPerimeterRatio() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getInscribedCircleCircumferenceToPerimeterRatio();

        // Then
        assertEquals(Math.PI / 4, result, 0.0001, "Відношення дуги вписаного кола до периметра має бути π/4 ≈ 0.7854");
    }

    @Test
    public void testGetCircumscribedCircleCircumferenceToPerimeterRatio() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getCircumscribedCircleCircumferenceToPerimeterRatio();

        // Then
        assertEquals(Math.PI * Math.sqrt(2) / 4, result, 0.0001, "Відношення дуги описаного кола до периметра має бути (π√2)/4 ≈ 1.1107");
    }

    @Test
    public void testGetInscribedCircleSectorArea() {
        // Given
        Square s = new Square(6);

        // When
        double result = s.getInscribedCircleSectorArea();

        // Then
        assertEquals(Math.PI * 9 / 4, result, 0.0001, "Площа сектора вписаного кола має бути 9π/4 ≈ 7.0686");
    }

    @Test
    public void testGetCircumscribedCircleSectorArea() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getCircumscribedCircleSectorArea();

        // Then
        assertEquals(Math.PI * 2, result, 0.0001, "Площа сектора описаного кола має бути 2π ≈ 6.2832");
    }

    @Test
    public void testGetDiagonalTriangleToInscribedCircleAreaRatio() {
        // Given
        Square s = new Square(2);

        // When
        double result = s.getDiagonalTriangleToInscribedCircleAreaRatio();

        // Then
        assertEquals(2 / Math.PI, result, 0.0001, "Відношення трикутника до вписаного кола має бути 2/π ≈ 0.6366");
    }

    @Test
    public void testGetDiagonalTriangleToCircumscribedCircleAreaRatio() {
        // Given
        Square s = new Square(4);

        // When
        double result = s.getDiagonalTriangleToCircumscribedCircleAreaRatio();

        // Then
        assertEquals(1 / Math.PI, result, 0.0001, "Відношення трикутника до описаного кола має бути 1/π ≈ 0.3183");
    }

    @Test
    public void testGetDiagonalTriangleAngleSumToSquareAngleSumRatio() {
        // Given
        Square s = new Square(5);

        // When
        double result = s.getDiagonalTriangleAngleSumToSquareAngleSumRatio();

        // Then
        assertEquals(0.5, result, 0.0001, "Відношення суми кутів трикутника до квадрата має бути 0.5");
    }
}