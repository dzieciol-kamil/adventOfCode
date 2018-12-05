package day25;

import java.util.HashMap;
import java.util.Map;

public class Input {
  public static final String INPUT =
      "Begin in state A.\n"
    + "Perform a diagnostic checksum after 12302209 steps.\n"
    + "\n"
    + "In state A:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state B.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the left.\n"
    + "    - Continue with state D.\n"
    + "\n"
    + "In state B:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state C.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state F.\n"
    + "\n"
    + "In state C:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the left.\n"
    + "    - Continue with state C.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the left.\n"
    + "    - Continue with state A.\n"
    + "\n"
    + "In state D:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the left.\n"
    + "    - Continue with state E.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state A.\n"
    + "\n"
    + "In state E:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 1.\n"
    + "    - Move one slot to the left.\n"
    + "    - Continue with state A.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state B.\n"
    + "\n"
    + "In state F:\n"
    + "  If the current value is 0:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state C.\n"
    + "  If the current value is 1:\n"
    + "    - Write the value 0.\n"
    + "    - Move one slot to the right.\n"
    + "    - Continue with state E.";


  public static Character parseStartState(String input) {
    return input.split("\n")[0].split(" ")[3].charAt(0);
  }

  public static int parseSteps(String input) {
    return Integer.parseInt(input.split("\n")[1].split(" ")[5]);
  }

  public static Map<Character, State> parseStates(String input) {
    String[] steps = input.split("\n\n");
    Map<Character, State> result = new HashMap<>();
    for (int i = 1; i < steps.length; i++) {
      parseState(steps[i], result);
    }
    return result;
  }

  private static void parseState(String step, Map<Character, State> result) {
    String[] stateLines = step.split("\n");
    Character stateName = stateLines[0].split(" ")[2].charAt(0);
    StateValues zero = getStateValues(stateLines[2], stateLines[3], stateLines[4]);
    StateValues one = getStateValues(stateLines[6], stateLines[7], stateLines[8]);
    State state = new State(zero.getValue(),
                            zero.getPosition(),
                            zero.getState(),
                            one.getValue(),
                            one.getPosition(),
                            one.getState());
    result.put(stateName, state);
  }

  private static StateValues getStateValues(String valueLine,
                                            String positionLine,
                                            String stateLine) {
    int value = getValue(valueLine);
    int position = getPosition(positionLine);
    Character state = getState(stateLine);
    return new StateValues(value, position, state);
  }

  private static int getValue(String valueLine) {
    return Integer.parseInt(valueLine.split(" ")[8].substring(0, 1));
  }

  private static int getPosition(String positionLine) {
    return positionLine.split(" ")[10].charAt(0) == 'r' ? 1 : -1;
  }

  private static Character getState(String stateLine) {
    return stateLine.split(" ")[8].charAt(0);
  }

  private static class StateValues {

    private final int value;
    private final int position;
    private final Character state;

    public StateValues(int value, int position, Character state) {
      this.value = value;
      this.position = position;
      this.state = state;
    }

    public int getValue() {
      return value;
    }

    public int getPosition() {
      return position;
    }

    public Character getState() {
      return state;
    }
  }
}
