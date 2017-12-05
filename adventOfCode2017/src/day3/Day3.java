package day3;

import advent.AdventClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

public class Day3 implements AdventClass {

  private static final int INPUT = 361527;

  @Override
  public String printFirst() {
    return "Shortest path steps count = " + calcSteps(INPUT, this::generateProgressive);
  }

  @Override
  public String printSecond() {
    return "Biggest number in this strange circle = " + this.generateAOIE(INPUT);
  }

  private int calcSteps(int input, Function<Integer, long[][]> generator) {
    long[][] table = generator.apply(input);
    List<long[]> columnList = Arrays.asList(table);
    for (long[] column : columnList) {
      boolean found = false;
      int colPos = 0;

      List<Long> colList = new ArrayList<>();
      for (long i : column) {
        colList.add(i);
      }

      for (Long element : colList) {
        if (element == input) {
          colPos = colList.indexOf(element) - colList.size() / 2;
          found = true;
          break;
        }
      }
      if (found) {
        int linePos = columnList.indexOf(column) - columnList.size() / 2;
        return Math.abs(linePos) + Math.abs(colPos);
      }
    }
    return 0;
  }

  private long[][] generateProgressive(int input) {
    int size;
    for (size = 1; size * size < input; size += 2) {
    }

    long[][] result = new long[size][size];

    int value = 1;
    int middle = size / 2;

    result[middle][middle] = value++;
    int layer = 1;
    while (value < input + 1) {
      int x = layer;
      int y = 1 - layer;
      for (; y < layer; y++) {
        result[x + middle][-y + middle] = value++;
      }
      for (; x > -layer; x--) {
        result[x + middle][-y + middle] = value++;
      }
      for (; y > -layer; y--) {
        result[x + middle][-y + middle] = value++;
      }
      for (; x <= layer; x++) {
        result[x + middle][-y + middle] = value++;
      }
      layer++;
    }

    return result;
  }

  private long generateAOIE(long input) {
    int size = 13;

    long[][] result = new long[size][size];

    int middle = size / 2;
    result[middle][middle] = 1;
    int layer = 1;
    boolean found = false;
    long calcValue = 0;
    while (!found && layer <= middle) {
      int x = layer;
      int y = 1 - layer;
      for (; y < layer; y++) {
        calcValue = calcValue(result, x + middle, -y + middle);
        if (calcValue > input) {
          found = true;
          break;
        }
        result[x + middle][-y + middle] = calcValue;
      }
      if (found)
        break;
      for (; x > -layer; x--) {
        calcValue = calcValue(result, x + middle, -y + middle);
        if (calcValue > input) {
          found = true;
          break;
        }
        result[x + middle][-y + middle] = calcValue;
      }
      if (found)
        break;
      for (; y > -layer; y--) {
        calcValue = calcValue(result, x + middle, -y + middle);
        if (calcValue > input) {
          found = true;
          break;
        }
        result[x + middle][-y + middle] = calcValue;
      }
      if (found)
        break;
      for (; x <= layer; x++) {
        calcValue = calcValue(result, x + middle, -y + middle);
        if (calcValue > input) {
          found = true;
          break;
        }
        result[x + middle][-y + middle] = calcValue;
      }
      layer++;
    }

    return calcValue;
  }

  private long calcValue(long[][] table, int x, int y) {
    long result = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0)
          continue;
        int x_calculated = x + i;
        int y_calculated = y + j;
        if (0 < x_calculated && x_calculated < table.length &&
            0 < y_calculated && y_calculated < table.length)
          result += table[x_calculated][y_calculated];
      }
    }
    return result;
  }


  @Test
  public void testStepCount() {
    checkCalcSteps(11, 2);
    checkCalcSteps(10, 3);
    checkCalcSteps(9, 2);
    checkCalcSteps(8, 1);
    checkCalcSteps(7, 2);
    checkCalcSteps(6, 1);
    checkCalcSteps(5, 2);
    checkCalcSteps(4, 1);
    checkCalcSteps(3, 2);
    checkCalcSteps(2, 1);
    checkCalcSteps(1, 0);
    checkCalcSteps(12, 3);
    checkCalcSteps(13, 4);
    checkCalcSteps(14, 3);
    checkCalcSteps(15, 2);
    checkCalcSteps(16, 3);
    checkCalcSteps(17, 4);
    checkCalcSteps(18, 3);
    checkCalcSteps(19, 2);
    checkCalcSteps(20, 3);
    checkCalcSteps(21, 4);
    checkCalcSteps(22, 3);
    checkCalcSteps(23, 2);
    checkCalcSteps(24, 3);
    checkCalcSteps(25, 4);
    checkCalcSteps(26, 5);
    checkCalcSteps(27, 4);
    checkCalcSteps(28, 3);
  }

  private void checkCalcSteps(int input, int expected) {
    assertEquals(expected, calcSteps(input, this::generateProgressive));
  }
}
