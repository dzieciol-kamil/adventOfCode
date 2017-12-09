package day8;

import advent.AdventClass;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Day8 implements AdventClass {

  private Map<String, Integer> register;
  private int highestValueEver;

  @Override
  public String printFirst() {
    register = generateRegister(Parser.parseInput(this, Input.INPUT));
    return "Largest value in registry = " + findLargestValue(register);
  }

  @Override
  public String printSecond() {
    register = generateRegister(Parser.parseInput(this, Input.INPUT));
    return "Largest value in registry = " + highestValueEver;
  }

  private int findLargestValue(Map<String, Integer> registry) {
    return registry.values().stream().max(Comparator.naturalOrder()).orElse(0);
  }

  private Map<String, Integer> generateRegister(List<Operation> input) {
    Map<String, Integer> result = new HashMap<>();
    input.forEach(operation -> applyOperation(operation, result));
    return result;
  }

  private void applyOperation(Operation operation,
                              Map<String, Integer> result) {
    operation.apply(result);
    int largestValue = findLargestValue(result);
    if (highestValueEver < largestValue)
      highestValueEver = largestValue;
  }

  @Test
  public void testApplyOperation() {
    Operation operation = new Operation("yxr",
                                        OperationType.INC,
                                        119,
                                        new Condition("nev", ConditionType.NE, 6));
    Map<String, Integer> testRegister = new HashMap<>();

    applyOperation(operation, testRegister);

    assertTrue(testRegister.keySet().containsAll(Arrays.asList("yxr", "nev")));
    assertEquals(119, testRegister.get("yxr").intValue());
    assertEquals(0, testRegister.get("nev").intValue());
  }

}
