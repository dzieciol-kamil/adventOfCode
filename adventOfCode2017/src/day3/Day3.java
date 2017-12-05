package day3;

import advent.AdventClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class Day3 implements AdventClass {

  private static final int INPUT = 361527;

  @Override
  public String printFirst() {
    return "Shortest path steps count = " + calcSteps(INPUT, this::progressiveGen);
  }

  @Override
  public String printSecond() {
    try {
      sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Shortest path steps count with AOIE";
  }

  private int calcSteps(int input, Function<Integer, List<List<Integer>>> generator) {
    List<List<Integer>> table = generator.apply(input);
    for (List<Integer> line : table) {
      boolean found = false;
      int linePos = 0;
      for (Integer element : line) {
        if (element == input) {
          linePos = line.indexOf(element) + 1 - (line.size() / 2 + 1);
          found = true;
          break;
        }
      }
      if (found) {
        int colPos = table.indexOf(line) + 1 - (table.size() / 2 + 1);
        return Math.abs(colPos) + Math.abs(linePos);
      }
    }
    return 0;
  }

  private List<List<Integer>> progressiveGen(int input) {
    int size;
    for (size = 1; size * size < input; size += 2) {}

    List<List<Integer>> result;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {

      }
    }
    return Arrays.asList(Arrays.asList(5,4,3),
                         Arrays.asList(6,1,2),
                         Arrays.asList(7,8,9));
  }

  @Test
  public void testStepCount() {
//    checkCalcSteps(11, 2);
//    checkCalcSteps(10, 3);
    checkCalcSteps(9, 2);
    checkCalcSteps(8, 1);
    checkCalcSteps(7, 2);
    checkCalcSteps(6, 1);
    checkCalcSteps(5, 2);
    checkCalcSteps(4, 1);
    checkCalcSteps(3, 2);
    checkCalcSteps(2, 1);
    checkCalcSteps(1, 0);
//    checkCalcSteps(12, 3);
//    checkCalcSteps(13, 4);
//    checkCalcSteps(14, 3);
//    checkCalcSteps(15, 2);
//    checkCalcSteps(16, 3);
//    checkCalcSteps(17, 4);
//    checkCalcSteps(18, 3);
//    checkCalcSteps(19, 2);
//    checkCalcSteps(20, 3);
//    checkCalcSteps(21, 4);
//    checkCalcSteps(22, 3);
//    checkCalcSteps(23, 2);
//    checkCalcSteps(24, 3);
//    checkCalcSteps(25, 4);
//    checkCalcSteps(26, 5);
//    checkCalcSteps(27, 4);
//    checkCalcSteps(28, 3);
  }

  private void checkCalcSteps(int input,int expected) {
    assertEquals(expected, calcSteps(input, this::progressiveGen));
  }
}
