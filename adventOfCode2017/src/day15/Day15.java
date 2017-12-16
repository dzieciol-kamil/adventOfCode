package day15;

import advent.AdventClass;

public class Day15 implements AdventClass{
  private final int GEN_A = 516;
  private final int GEN_B = 190;

  @Override
  public String printFirst() {
    Judge judge = new Judge(GEN_A, GEN_B, 1, 1, 40_000_000);
    return "Judge's final count = " + judge.getFinalCount();
  }

  @Override
  public String printSecond() {
    Judge judge = new Judge(GEN_A, GEN_B, 4, 8, 5_000_000);
    return "Judge's final count = " + judge.getFinalCount();
  }
}
