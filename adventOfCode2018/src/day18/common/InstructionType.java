package day18.common;

public enum InstructionType {
  SOUND("snd"),
  SET("set"),
  ADD("add"),
  MULTIPLY("mul"),
  MODULO("mod"),
  RECOVER("rcv"),
  JUMP("jgz");

  private final String value;

  InstructionType(String value) {
    this.value = value;
  }

  public static InstructionType of(String text) {
    for (InstructionType type : InstructionType.values()) {
      if (type.value.equalsIgnoreCase(text)) {
        return type;
      }
    }
    return null;
  }
}
