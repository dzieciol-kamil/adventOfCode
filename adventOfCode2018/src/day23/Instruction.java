package day23;

import java.util.Map;

public class Instruction {

  private final InstructionType type;
  private final InstructionValue firstValue;
  private final InstructionValue secondValue;

  public Instruction(InstructionType type,
                     InstructionValue firstValue,
                     InstructionValue secondValue) {
    this.type = type;
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }

  public int invoke(Map<Character, Long> register, int actualPosition) {
    return type.invoke(register, actualPosition, firstValue, secondValue);
  }

  public int multiplyCounterUpdate(int multiplyCount) {
    return type.equals(InstructionType.MULTIPLY) ? (multiplyCount += 1) : multiplyCount;
  }

  @Override
  public String toString() {
    return "Instruction{" +
        "type=" + type +
        ", first=" + firstValue +
        ", second=" + secondValue +
        '}';
  }
}
