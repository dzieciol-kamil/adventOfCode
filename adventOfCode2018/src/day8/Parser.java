package day8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Parser {

  public static List<Operation> parseInput(Day8 day8, String input) {
    List<Operation> result = new ArrayList<>();
    for (String line : Arrays.asList(input.split("\n"))) {
      result.add(generateOperation(line));
    }
    return result;
  }

  private static Operation generateOperation(String line) {
    List<String> operationToParse = Arrays.asList(line.split(" "));
    String register = operationToParse.get(0);
    OperationType type = OperationType.of(operationToParse.get(1));
    int value = Integer.parseInt(operationToParse.get(2));
    Condition condition = generateCondition(operationToParse.subList(4, operationToParse.size()));
    return new Operation(register, type, value, condition);
  }

  @Test
  public static void testGenerateOperation() {
    String input = "yxr inc 119 if nev != 6";

    Operation operation = generateOperation(input);

    assertEquals(new Operation("yxr",
                               OperationType.INC,
                               119,
                               new Condition("nev", ConditionType.NE, 6)), operation);

  }

  private static Condition generateCondition(List<String> conditionToParse) {
    String register = conditionToParse.get(0);
    ConditionType conditionType = ConditionType.of(conditionToParse.get(1));
    int value = Integer.parseInt(conditionToParse.get(2));
    return new Condition(register, conditionType, value);
  }

  @Test
  public static void testGenerateCondition() {
    List<String> input = Arrays.asList("a", ">", "3");

    Condition condition = generateCondition(input);

    assertEquals(new Condition("a", ConditionType.GT, 3), condition);
  }
}
