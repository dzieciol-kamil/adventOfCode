package day8;

import advent.AdventClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day8 implements AdventClass {

  private Map<String, Integer> register;
  private int highestValueEver;

  @Override
  public String printFirst() {
    register = generateRegister(parseInput(Input.INPUT));
    return "Largest value in registry = " + findLargestValue(register);
  }

  private List<Operation> parseInput(String input) {
    List<Operation> result = new ArrayList<>();
    for (String line : Arrays.asList(input.split("\n"))) {
      result.add(generateOperation(line));
    }
    return result;
  }

  private Operation generateOperation(String line) {
    List<String> operationToParse = Arrays.asList(line.split(" "));
    String register = operationToParse.get(0);
    OperationType type = OperationType.of(operationToParse.get(1));
    int value = Integer.parseInt(operationToParse.get(2));
    Condition condition = generateCondition(operationToParse.subList(4, operationToParse.size()));
    return new Operation(register, type, value, condition);
  }

  @Test
  public void testGenerateOperation() {
    String input = "yxr inc 119 if nev != 6";

    Operation operation = generateOperation(input);

    assertEquals(new Operation("yxr",
                               OperationType.INC,
                               119,
                               new Condition("nev", ConditionType.NE, 6)), operation);

  }

  private Condition generateCondition(List<String> conditionToParse) {
    String register = conditionToParse.get(0);
    ConditionType conditionType = ConditionType.of(conditionToParse.get(1));
    int value = Integer.parseInt(conditionToParse.get(2));
    return new Condition(register, conditionType, value);
  }

  @Test
  public void testGenerateCondition() {
    List<String> input = Arrays.asList("a", ">", "3");

    Condition condition = generateCondition(input);

    assertEquals(new Condition("a", ConditionType.GT, 3), condition);
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

  @Override
  public String printSecond() {
    register = generateRegister(parseInput(Input.INPUT));
    return "Largest value in registry = " + highestValueEver;
  }

}
