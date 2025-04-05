

import org.example.IntToRom;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntToRomTest {

    @Test
    public void testConvert1() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 1;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("I", result, "1 має бути 'I'");
    }

    @Test
    public void testConvert2() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 2;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("II", result, "2 має бути 'II'");
    }

    @Test
    public void testConvert3() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("III", result, "3 має бути 'III'");
    }

    @Test
    public void testConvert4() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 4;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("IV", result, "4 має бути 'IV'");
    }

    @Test
    public void testConvert5() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 5;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("V", result, "5 має бути 'V'");
    }

    @Test
    public void testConvert6() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 6;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("VI", result, "6 має бути 'VI'");
    }

    @Test
    public void testConvert7() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 7;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("VII", result, "7 має бути 'VII'");
    }

    @Test
    public void testConvert8() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 8;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("VIII", result, "8 має бути 'VIII'");
    }

    @Test
    public void testConvert9() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 9;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("IX", result, "9 має бути 'IX'");
    }

    @Test
    public void testConvert10() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 10;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("X", result, "10 має бути 'X'");
    }

    @Test
    public void testConvert11() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 11;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XI", result, "11 має бути 'XI'");
    }

    @Test
    public void testConvert14() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 14;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XIV", result, "14 має бути 'XIV'");
    }

    @Test
    public void testConvert19() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 19;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XIX", result, "19 має бути 'XIX'");
    }

    @Test
    public void testConvert20() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 20;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XX", result, "20 має бути 'XX'");
    }

    @Test
    public void testConvert30() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 30;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XXX", result, "30 має бути 'XXX'");
    }

    @Test
    public void testConvert40() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 40;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XL", result, "40 має бути 'XL'");
    }

    @Test
    public void testConvert49() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 49;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XLIX", result, "49 має бути 'XLIX'");
    }

    @Test
    public void testConvert50() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 50;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("L", result, "50 має бути 'L'");
    }

    @Test
    public void testConvert60() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 60;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LX", result, "60 має бути 'LX'");
    }

    @Test
    public void testConvert70() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 70;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LXX", result, "70 має бути 'LXX'");
    }

    @Test
    public void testConvert80() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 80;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LXXX", result, "80 має бути 'LXXX'");
    }

    @Test
    public void testConvert90() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 90;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XC", result, "90 має бути 'XC'");
    }

    @Test
    public void testConvert99() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 99;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XCIX", result, "99 має бути 'XCIX'");
    }

    @Test
    public void testConvert100() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 100;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("C", result, "100 має бути 'C'");
    }

    @Test
    public void testConvert200() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 200;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CC", result, "200 має бути 'CC'");
    }

    @Test
    public void testConvert300() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 300;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CCC", result, "300 має бути 'CCC'");
    }

    @Test
    public void testConvert400() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 400;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CD", result, "400 має бути 'CD'");
    }

    @Test
    public void testConvert500() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 500;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("D", result, "500 має бути 'D'");
    }

    @Test
    public void testConvert600() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 600;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DC", result, "600 має бути 'DC'");
    }

    @Test
    public void testConvert700() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 700;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DCC", result, "700 має бути 'DCC'");
    }

    @Test
    public void testConvert800() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 800;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DCCC", result, "800 має бути 'DCCC'");
    }

    @Test
    public void testConvert900() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 900;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CM", result, "900 має бути 'CM'");
    }

    @Test
    public void testConvert999() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 999;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CMXCIX", result, "999 має бути 'CMXCIX'");
    }

    @Test
    public void testConvert1000() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 1000;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("M", result, "1000 має бути 'M'");
    }

    @Test
    public void testConvert1500() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 1500;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MD", result, "1500 має бути 'MD'");
    }

    @Test
    public void testConvert2000() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 2000;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MM", result, "2000 має бути 'MM'");
    }

    @Test
    public void testConvert3000() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3000;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMM", result, "3000 має бути 'MMM'");
    }

    @Test
    public void testConvert3999() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3999;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMMCMXCIX", result, "3999 має бути 'MMMCMXCIX'");
    }

    @Test
    public void testConvertZeroThrowsException() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 0;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Number must be positive and greater than 0", exception.getMessage(), "Очікується помилка для 0");
    }

    @Test
    public void testConvertNegativeThrowsException() {
        // Given
        IntToRom converter = new IntToRom();
        int input = -39;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Number must be positive and greater than 0", exception.getMessage(), "Очікується помилка для від’ємного числа");
    }

    @Test
    public void testConvertOverLimitThrowsException() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 4000;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convert(input));
        assertEquals("Number exceeds maximum Roman numeral value of 3999", exception.getMessage(), "Очікується помилка для числа > 3999");
    }

    @Test
    public void testConvert44() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 44;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XLIV", result, "44 має бути 'XLIV'");
    }

    @Test
    public void testConvert94() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 94;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XCIV", result, "94 має бути 'XCIV'");
    }

    @Test
    public void testConvert444() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 444;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CDXLIV", result, "444 має бути 'CDXLIV'");
    }

    @Test
    public void testConvert15() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 15;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XV", result, "15 має бути 'XV'");
    }

    @Test
    public void testConvert25() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 25;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XXV", result, "25 має бути 'XXV'");
    }

    @Test
    public void testConvert35() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 35;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XXXV", result, "35 має бути 'XXXV'");
    }

    @Test
    public void testConvert45() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 45;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XLV", result, "45 має бути 'XLV'");
    }

    @Test
    public void testConvert55() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 55;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LV", result, "55 має бути 'LV'");
    }

    @Test
    public void testConvert65() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 65;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LXV", result, "65 має бути 'LXV'");
    }

    @Test
    public void testConvert75() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 75;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LXXV", result, "75 має бути 'LXXV'");
    }

    @Test
    public void testConvert85() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 85;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("LXXXV", result, "85 має бути 'LXXXV'");
    }

    @Test
    public void testConvert95() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 95;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("XCV", result, "95 має бути 'XCV'");
    }

    @Test
    public void testConvert149() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 149;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CXLIX", result, "149 має бути 'CXLIX'");
    }

    @Test
    public void testConvert249() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 249;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CCXLIX", result, "249 має бути 'CCXLIX'");
    }

    @Test
    public void testConvert349() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 349;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CCCXLIX", result, "349 має бути 'CCCXLIX'");
    }

    @Test
    public void testConvert449() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 449;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CDXLIX", result, "449 має бути 'CDXLIX'");
    }

    @Test
    public void testConvert549() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 549;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DXLIX", result, "549 має бути 'DXLIX'");
    }

    @Test
    public void testConvert649() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 649;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DCXLIX", result, "649 має бути 'DCXLIX'");
    }

    @Test
    public void testConvert749() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 749;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DCCXLIX", result, "749 має бути 'DCCXLIX'");
    }

    @Test
    public void testConvert849() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 849;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("DCCCXLIX", result, "849 має бути 'DCCCXLIX'");
    }

    @Test
    public void testConvert949() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 949;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("CMXLIX", result, "949 має бути 'CMXLIX'");
    }

    @Test
    public void testConvert1234() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 1234;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MCCXXXIV", result, "1234 має бути 'MCCXXXIV'");
    }

    @Test
    public void testConvert2345() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 2345;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMCCCXLV", result, "2345 має бути 'MMCCCXLV'");
    }

    @Test
    public void testConvert3456() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3456;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMMCDLVI", result, "3456 має бути 'MMMCDLVI'");
    }

    @Test
    public void testConvertLargeNumber1945() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 1945;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MCMXLV", result, "1945 має бути 'MCMXLV'");
    }

    @Test
    public void testConvertLargeNumber2768() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 2768;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMDCCLXVIII", result, "2768 має бути 'MMDCCLXVIII'");
    }

    @Test
    public void testConvertLargeNumber3888() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3888;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMMDCCCLXXXVIII", result, "3888 має бути 'MMMDCCCLXXXVIII'");
    }

    @Test
    public void testConvertVeryLargeNumber3998() {
        // Given
        IntToRom converter = new IntToRom();
        int input = 3998;

        // When
        String result = converter.convert(input);

        // Then
        assertEquals("MMMCMXCVIII", result, "3998 має бути 'MMMCMXCVIII'");
    }
}