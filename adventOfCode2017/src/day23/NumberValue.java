package day23;

import java.util.Map;

public class NumberValue implements InstructionValue {

  private final Long value;

  public NumberValue(Long value) {
    this.value = value;
  }

  @Override
  public Long getValue(Map<Character, Long> register) {
    return value;
  }

  @Override
  public String toString() {
    return "NumberValue{" +
        "value=" + value +
        '}';
  }
}
