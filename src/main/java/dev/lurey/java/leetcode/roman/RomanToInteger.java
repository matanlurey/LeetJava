package dev.lurey.java.leetcode.roman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Given a roman numeral, converts it to an integer.
 *
 * <ul>
 *   <li>I: 1
 *   <li>V: 5
 *   <li>X: 10
 *   <li>L: 50
 *   <li>C: 100
 *   <li>D: 500
 *   <li>M: 1000
 * </ul>
 *
 * <p>See: https://leetcode.com/problems/roman-to-integer/
 */
public final class RomanToInteger {
  public static void main(String[] args) {
    if (args.length == 0) {
      Scanner in = new Scanner(System.in);
      String input;
      while (!(input = in.nextLine()).equals("")) {
        System.out.println(convert(input));
      }
    } else if (args.length > 1) {
      System.err.printf("Expected 0 to 1 arguments, got %s%n", args.length);
      System.exit(1);
    } else {
      System.out.println(convert(args[0]));
    }
  }

  public static int convert(String input) {
    return compute(parse(input));
  }

  private static List<Numerals> parse(String input) {
    List<Numerals> result = new ArrayList<>();
    Symbol current = null;
    int count = 0;
    for (char c : input.toCharArray()) {
      Symbol s = Symbol.valueOf(Character.toString(c));
      if (current == null) {
        // Initial character/numeral.
        current = s;
        count = 1;
      } else if (current == s) {
        // Increment the count of the previous numeral.
        count++;
      } else {
        // Numeral is switching, so add the previous, and start a new one.
        result.add(new Numerals(current, count));
        current = s;
        count = 1;
      }
    }
    if (count > 0) {
      // We had an accumulating numeral we didn't add yet.
      result.add(new Numerals(current, count));
    }
    return result;
  }

  private static int compute(List<Numerals> numerals) {
    int total = 0;
    for (int i = 0; i < numerals.size(); i++) {
      Numerals current = numerals.get(i);
      Optional<Numerals> next = Optional.empty();
      if (i + 1 < numerals.size()) {
        next = Optional.of(numerals.get(i + 1));
      }
      int value = compute(current, next);
      if (value != current.getValue()) {
        i++;
      }
      total += value;
    }
    return total;
  }

  private static int compute(Numerals current, Optional<Numerals> next) {
    return next.isEmpty() ? current.getValue() : compute(current, next.orElseThrow());
  }

  private static int compute(Numerals current, Numerals next) {
    boolean subtract = false;
    if (next.getCount() == 1) {
      switch (current.getSymbol()) {
        case I:
          switch (next.getSymbol()) {
            case V, X -> subtract = true;
          }
        case X:
          switch (next.getSymbol()) {
            case L, C -> subtract = true;
          }
        case C:
          switch (next.getSymbol()) {
            case D, M -> subtract = true;
          }
      }
    }
    return subtract ? next.getValue() - current.getValue() : current.getValue();
  }
}
