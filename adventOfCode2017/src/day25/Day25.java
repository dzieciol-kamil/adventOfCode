package day25;

import advent.AdventClass;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day25 implements AdventClass {

  @Override
  public String printFirst() {
    TuringMachine turingMachine = new TuringMachine(Input.parseStates(Input.INPUT));
    turingMachine.run(Input.parseStartState(Input.INPUT), Input.parseSteps(Input.INPUT));
    return "Checksum = " + turingMachine.getChecksum();
  }

  @Override
  public String printSecond() {
    return null;
  }

  @Test
  public void test() {
    String input = "Begin in state A.\n"
        + "Perform a diagnostic checksum after 6 steps.\n"
        + "\n"
        + "In state A:\n"
        + "  If the current value is 0:\n"
        + "    - Write the value 1.\n"
        + "    - Move one slot to the right.\n"
        + "    - Continue with state B.\n"
        + "  If the current value is 1:\n"
        + "    - Write the value 0.\n"
        + "    - Move one slot to the left.\n"
        + "    - Continue with state B.\n"
        + "\n"
        + "In state B:\n"
        + "  If the current value is 0:\n"
        + "    - Write the value 1.\n"
        + "    - Move one slot to the left.\n"
        + "    - Continue with state A.\n"
        + "  If the current value is 1:\n"
        + "    - Write the value 1.\n"
        + "    - Move one slot to the right.\n"
        + "    - Continue with state A.";
    TuringMachine turingMachine = new TuringMachine(Input.parseStates(input));
    turingMachine.run(Input.parseStartState(input), Input.parseSteps(input));
    assertEquals(3, turingMachine.getChecksum());
  }
}
