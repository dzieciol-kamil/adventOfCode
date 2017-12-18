package day18.common;

import java.util.HashMap;
import java.util.function.Function;

public class Instruction {
  private final InstructionType type;
  private final boolean alwaysOn;
  private final Character register;
  private final Character registerWithValue;
  private final boolean registerValue;
  private final Long value;

  public Instruction(InstructionType type,
                     boolean alwaysOn,
                     Character register,
                     boolean registerValue,
                     Character registerWithValue,
                     Long value) {
    this.type = type;
    this.alwaysOn = alwaysOn;
    this.register = register;
    this.registerWithValue = registerWithValue;
    this.registerValue = registerValue;
    this.value = value;
  }

  public InstructionType getType() {
    return type;
  }

  public boolean isOn(HashMap<Character, Long> registerMap, Function<Long, Boolean> condition) {
    if (alwaysOn)
      return true;

    return condition.apply(registerMap.getOrDefault(register, 0l));
  }

  public Character getRegister() {
    return register;
  }

  public Long getValue(HashMap<Character, Long> register) {
    if (registerValue)
      return register.getOrDefault(registerWithValue, 0l);
    else
      return value;
  }

  public static Instruction parse(String s) {
    String[] split = s.split(" ");
    InstructionType type = InstructionType.of(split[0]);
    boolean registerValue = true;
    Long intValue = -1l;

    Character registerWithValue = null;
    if (split.length > 2) {
      registerWithValue = split[2].charAt(0);
      try {
        intValue = Long.parseLong(split[2]);
        registerValue = false;
      } catch (NumberFormatException ignored) {
      }
    }

    return new Instruction(type,
                           isAlwaysOn(split[1]),
                           split[1].charAt(0),
                           registerValue,
                           registerWithValue,
                           intValue);
  }

  private static boolean isAlwaysOn(String s) {
    boolean alwaysOn = false;
    try {
      Integer.parseInt(s);
      alwaysOn = true;
    } catch (NumberFormatException ignored) {}
    return alwaysOn;
  }
}
