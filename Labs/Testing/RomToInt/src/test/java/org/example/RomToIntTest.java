package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RomToIntTest {

    private final RomToInt converter = new RomToInt();

    @Test
    public void testConvertI() {
        // Given
        String input = "I";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(1, result, "I має бути 1");
    }

    @Test
    public void testConvertII() {
        // Given
        String input = "II";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(2, result, "II має бути 2");
    }

    @Test
    public void testConvertIII() {
        // Given
        String input = "III";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(3, result, "III має бути 3");
    }

    @Test
    public void testConvertIV() {
        // Given
        String input = "IV";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(4, result, "IV має бути 4");
    }

    @Test
    public void testConvertV() {
        // Given
        String input = "V";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(5, result, "V має бути 5");
    }

    @Test
    public void testConvertVI() {
        // Given
        String input = "VI";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(6, result, "VI має бути 6");
    }

    @Test
    public void testConvertVII() {
        // Given
        String input = "VII";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(7, result, "VII має бути 7");
    }

    @Test
    public void testConvertVIII() {
        // Given
        String input = "VIII";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(8, result, "VIII має бути 8");
    }

    @Test
    public void testConvertIX() {
        // Given
        String input = "IX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(9, result, "IX має бути 9");
    }

    @Test
    public void testConvertX() {
        // Given
        String input = "X";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(10, result, "X має бути 10");
    }

    @Test
    public void testConvertXI() {
        // Given
        String input = "XI";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(11, result, "XI має бути 11");
    }

    @Test
    public void testConvertXIV() {
        // Given
        String input = "XIV";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(14, result, "XIV має бути 14");
    }

    @Test
    public void testConvertXIX() {
        // Given
        String input = "XIX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(19, result, "XIX має бути 19");
    }

    @Test
    public void testConvertXX() {
        // Given
        String input = "XX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(20, result, "XX має бути 20");
    }

    @Test
    public void testConvertXXX() {
        // Given
        String input = "XXX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(30, result, "XXX має бути 30");
    }

    @Test
    public void testConvertXL() {
        // Given
        String input = "XL";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(40, result, "XL має бути 40");
    }

    @Test
    public void testConvertL() {
        // Given
        String input = "L";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(50, result, "L має бути 50");
    }

    @Test
    public void testConvertLX() {
        // Given
        String input = "LX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(60, result, "LX має бути 60");
    }

    @Test
    public void testConvertLXX() {
        // Given
        String input = "LXX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(70, result, "LXX має бути 70");
    }

    @Test
    public void testConvertLXXX() {
        // Given
        String input = "LXXX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(80, result, "LXXX має бути 80");
    }

    @Test
    public void testConvertXC() {
        // Given
        String input = "XC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(90, result, "XC має бути 90");
    }

    @Test
    public void testConvertXCIX() {
        // Given
        String input = "XCIX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(99, result, "XCIX має бути 99");
    }

    @Test
    public void testConvertC() {
        // Given
        String input = "C";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(100, result, "C має бути 100");
    }

    @Test
    public void testConvertCC() {
        // Given
        String input = "CC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(200, result, "CC має бути 200");
    }

    @Test
    public void testConvertCCC() {
        // Given
        String input = "CCC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(300, result, "CCC має бути 300");
    }

    @Test
    public void testConvertCD() {
        // Given
        String input = "CD";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(400, result, "CD має бути 400");
    }

    @Test
    public void testConvertD() {
        // Given
        String input = "D";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(500, result, "D має бути 500");
    }

    @Test
    public void testConvertDC() {
        // Given
        String input = "DC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(600, result, "DC має бути 600");
    }

    @Test
    public void testConvertDCC() {
        // Given
        String input = "DCC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(700, result, "DCC має бути 700");
    }

    @Test
    public void testConvertDCCC() {
        // Given
        String input = "DCCC";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(800, result, "DCCC має бути 800");
    }

    @Test
    public void testConvertCM() {
        // Given
        String input = "CM";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(900, result, "CM має бути 900");
    }

    @Test
    public void testConvertM() {
        // Given
        String input = "M";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(1000, result, "M має бути 1000");
    }

    @Test
    public void testConvertMM() {
        // Given
        String input = "MM";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(2000, result, "MM має бути 2000");
    }

    @Test
    public void testConvertMMM() {
        // Given
        String input = "MMM";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(3000, result, "MMM має бути 3000");
    }

    @Test
    public void testConvertMMMCMXCIX() {
        // Given
        String input = "MMMCMXCIX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(3999, result, "MMMCMXCIX має бути 3999");
    }

    @Test
    public void testConvertLowerCase() {
        // Given
        String input = "mmmcmlxxxviii";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(3988, result, "mmmcmlxxxviii має бути 3988");
    }

    @Test
    public void testConvertComplexNumber() {
        // Given
        String input = "MCMLIV";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(1954, result, "MCMLIV має бути 1954");
    }

    @Test
    public void testConvertAnotherComplexNumber() {
        // Given
        String input = "MMXXI";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(2021, result, "MMXXI має бути 2021");
    }

    @Test
    public void testConvertEmptyStringThrowsException() {
        // Given
        String input = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Input cannot be null or empty", exception.getMessage(), "Очікується помилка для порожнього рядка");
    }

    @Test
    public void testConvertNullThrowsException() {
        // Given
        String input = null;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Input cannot be null or empty", exception.getMessage(), "Очікується помилка для null");
    }

    @Test
    public void testConvertInvalidCharacterThrowsException() {
        // Given
        String input = "ABC";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для невалідних символів");
    }

    @Test
    public void testConvertInvalidFormatThrowsException() {
        // Given
        String input = "IIII";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для невалідного формату IIII");
    }

    @Test
    public void testConvertInvalidRepeatingVThrowsException() {
        // Given
        String input = "VV";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для VV");
    }

    @Test
    public void testConvertInvalidRepeatingLThrowsException() {
        // Given
        String input = "LL";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для LL");
    }

    @Test
    public void testConvertInvalidRepeatingDThrowsException() {
        // Given
        String input = "DD";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для DD");
    }

    @Test
    public void testConvertInvalidSubtractionVXThrowsException() {
        // Given
        String input = "VX";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для VX");
    }

    @Test
    public void testConvertInvalidSubtractionLCThrowsException() {
        // Given
        String input = "LC";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Invalid Roman numeral format", exception.getMessage(), "Очікується помилка для LC");
    }

    @Test
    public void testConvertComplexNumberMMMCDXLIV() {
        // Given
        String input = "MMMCDXLIV";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(3444, result, "MMMCDXLIV має бути 3444");
    }

    @Test
    public void testConvertComplexNumberMCMXCIX() {
        // Given
        String input = "MCMXCIX";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(1999, result, "MCMXCIX має бути 1999");
    }

    @Test
    public void testConvertComplexNumberMDCLXVI() {
        // Given
        String input = "MDCLXVI";

        // When
        int result = converter.convert(input);

        // Then
        assertEquals(1666, result, "MDCLXVI має бути 1666");
    }
}