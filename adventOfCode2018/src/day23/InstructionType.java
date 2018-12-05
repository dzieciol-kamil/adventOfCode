package day23;

import java.util.Map;

public enum InstructionType {
  SET("set") {
    @Override
    public int invoke(Map<Character, Long> register,
                      int actualPosition,
                      InstructionValue firstValue,
                      InstructionValue secondValue) {
      ((RegisterValue) firstValue).setValue(register, secondValue.getValue(register));
      return actualPosition += 1;
    }
  },
  DECRESE("sub") {
    @Override
    public int invoke(Map<Character, Long> register,
                      int actualPosition,
                      InstructionValue firstValue,
                      InstructionValue secondValue) {
      Long value = firstValue.getValue(register) - secondValue.getValue(register) ;
      ((RegisterValue) firstValue).setValue(register, value);
      return actualPosition += 1;
    }
  },
  MULTIPLY("mul") {
    @Override
    public int invoke(Map<Character, Long> register,
                      int actualPosition,
                      InstructionValue firstValue,
                      InstructionValue secondValue) {
      Long value = firstValue.getValue(register) * secondValue.getValue(register) ;
      ((RegisterValue) firstValue).setValue(register, value);
      return actualPosition += 1;
    }
  },
  JUMP("jnz") {
    @Override
    public int invoke(Map<Character, Long> register,
                      int actualPosition,
                      InstructionValue firstValue,
                      InstructionValue secondValue) {
      return firstValue.getValue(register) != 0 ? (actualPosition += secondValue.getValue(register))
                                                : (actualPosition += 1);
    }
  };

  private final String stringValue;

  InstructionType(String value) {
    this.stringValue = value;
  }

  public static InstructionType of(String value) {
    for (InstructionType instructionType : InstructionType.values()) {
      if (instructionType.stringValue.equals(value))
        return instructionType;
    }
    throw new RuntimeException("You shall not pass");
  }

  public abstract int invoke(Map<Character, Long> register,
                             int actualPosition,
                             InstructionValue firstValue,
                             InstructionValue secondValue);
}
