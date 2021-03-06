package day23;

import advent.AdventClass;

import java.util.List;

public class Day23 implements AdventClass {

  @Override
  public String printFirst() {
    List<Instruction> instructions = Input.parse(Input.INPUT);
    Cooprocessor cooprocessor = new Cooprocessor(instructions, 0L);
    cooprocessor.runInDebug();
    return "The mul instruction was invoked = " + cooprocessor.getMultiplyCount() + " times";
  }

  @Override
  public String printSecond() {
    List<Instruction> instructions = Input.parse(Input.INPUT);
    Cooprocessor cooprocessor = new Cooprocessor(instructions, 1L);
    cooprocessor.run();
    return "The h value = " + cooprocessor.getRegisterH();
  }
}
