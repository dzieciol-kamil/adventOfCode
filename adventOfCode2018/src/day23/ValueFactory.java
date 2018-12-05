package day23;

public class ValueFactory {

  public InstructionValue create(String value) {
    try {
      Long i = Long.parseLong(value);
      return new NumberValue(i);
    } catch (NumberFormatException ignored) {
    }
    return new RegisterValue(value);
  }
}
