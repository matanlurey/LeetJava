package dev.lurey.java.leetcode.roman;

import java.util.Objects;

/** A set of symbols and the number of times it occurred. */
final class Numerals {
  private final Symbol symbol;
  private final int count;

  Numerals(Symbol symbol) {
    this(symbol, 1);
  }

  Numerals(Symbol symbol, int count) {
    this.symbol = symbol;
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Numerals numerals = (Numerals) o;
    return getCount() == numerals.getCount() && getSymbol() == numerals.getSymbol();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSymbol(), getCount());
  }

  Symbol getSymbol() {
    return symbol;
  }

  int getCount() {
    return count;
  }

  /** Returns the count of the collection of symbols. */
  int getValue() {
    return symbol.getValue() * getCount();
  }
}
