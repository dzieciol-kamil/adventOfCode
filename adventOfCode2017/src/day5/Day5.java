package day5;

import advent.AdventClass;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Day5 implements AdventClass{

  private static final String INPUT = Input.INPUT;

  @Override
  public String printFirst() {
    return "Required jumps with inc= " + calcJumps(getListOfIntInput(), this::increseValue);
  }

  @Override
  public String printSecond() {
    return "Required jumps with inc/dec = " + calcJumps(getListOfIntInput(), this::incDecValue);
  }

  private List<Integer> getListOfIntInput() {
    return Arrays.stream(INPUT.split("\n"))
                 .map(Integer::parseInt)
                 .collect(Collectors.toList());
  }

  private int calcJumps(List<Integer> jumps, Function<Integer, Integer> calcValueMethod) {
    int pos = 0;
    int count = 0;
    while (pos < jumps.size()) {
      Integer jumpValue = jumps.get(pos);
      int newPos = pos + jumpValue;
      jumps.set(pos, calcValueMethod.apply(jumpValue));
      pos = newPos;
      count++;
    }

    return count;
  }

  private Integer increseValue(Integer jumpValue) {
    return ++jumpValue;
  }

  private Integer incDecValue(Integer jumpValue) {
    return jumpValue >= 3 ? --jumpValue : ++jumpValue;
  }

  @Test
  public void testProcessor() {
    checkJumpCalc(Arrays.asList(0), 2);
    checkJumpCalc(Arrays.asList(1), 1);
    checkJumpCalc(Arrays.asList(2), 1);
    checkJumpCalc(Arrays.asList(1,1), 2);
    checkJumpCalc(Arrays.asList(2,1), 1);
    checkJumpCalc(Arrays.asList(1,1,1), 3);
    checkJumpCalc(Arrays.asList(2,1,-1), 5);
    checkJumpCalc(Arrays.asList(0, 3, 0, 1, -3), 5);
    assertEquals(10, calcJumps(Arrays.asList(0, 3, 0, 1, -3), this::incDecValue));
  }

  private void checkJumpCalc(List<Integer> jumps, int expected) {
    assertEquals(expected, calcJumps(jumps, this::increseValue));
  }

}
