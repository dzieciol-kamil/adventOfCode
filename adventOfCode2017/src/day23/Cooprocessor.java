package day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cooprocessor {

  private final List<Instruction> instructions;
  private final Map<Character, Long> register;
  private int actualPosition;
  private int multiplyCount;
  private int count = 1;

  public Cooprocessor(List<Instruction> instructions, Long aRegisterValue) {
    this.instructions = instructions;
    this.register = new HashMap() {{
      put('a', aRegisterValue);
      put('b', 0l);
      put('c', 0l);
      put('d', 0l);
      put('e', 0l);
      put('f', 0l);
      put('g', 0l);
      put('h', 0l);
    }};
    actualPosition = 0;
    multiplyCount = 0;
  }

  public int getMultiplyCount() {
    return multiplyCount;
  }

  public Long getRegisterH() {
    return register.get('h');
  }

  public void run() {
    while (actualPosition >= 0 && actualPosition < instructions.size()) {
      if (actualPosition == 21)
        fixSecondLoop();
      Instruction instruction = instructions.get(actualPosition);
      multiplyCount = instruction.multiplyCounterUpdate(multiplyCount);
      actualPosition = instruction.invoke(register, actualPosition);
    }
  }

  private void fixFirstLoop() {
    register.put('e', register.get('e') - register.get('g'));
    if (register.get('b') % 2 == 0)
      register.put('f', 0l);
    register.put('g', 0l);
  }

  private void fixSecondLoop() {
    register.put('d', register.get('b'));
    printRegister(actualPosition);
  }

  private void printRegister(int lastPos) {
    System.out.println(register + " pos " + (lastPos) + " count " + count++);
  }
}
