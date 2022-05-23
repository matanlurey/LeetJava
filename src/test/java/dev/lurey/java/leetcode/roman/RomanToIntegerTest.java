package dev.lurey.java.leetcode.roman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class RomanToIntegerTest {
  @Test
  void convert_III_to_3() {
    assertEquals(3, RomanToInteger.convert("III"));
  }

  @Test
  void convert_LVIII_to_58() {
    assertEquals(58, RomanToInteger.convert("LVIII"));
  }

  @Test
  void convert_MCMXCIV_to_1994() {
    assertEquals(1994, RomanToInteger.convert("MCMXCIV"));
  }
}
