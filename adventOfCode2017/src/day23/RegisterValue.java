package day23;

import java.util.Map;

public class RegisterValue implements InstructionValue {

  private final Character register;

  public RegisterValue(String register) {
    this.register = register.charAt(0);
  }

  @Override
  public Long getValue(Map<Character, Long> registerMap) {
    return registerMap.get(register);
  }

  public void setValue(Map<Character, Long> registerMap, Long value) {
    registerMap.put(register, value);
  }

  @Override
  public String toString() {
    return "RegisterValue{" +
        "register=" + register +
        '}';
  }
}
