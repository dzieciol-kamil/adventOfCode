package day8;

import java.util.Map;
import java.util.Objects;

class Condition {
  private final String registry;
  private final ConditionType type;
  private final int value;

  public Condition(String registry, ConditionType type, int value) {
    this.registry = registry;
    this.type = type;
    this.value = value;
  }

  public String getRegistry() {
    return registry;
  }

  public ConditionType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Condition condition = (Condition) o;
    return value == condition.value &&
        Objects.equals(registry, condition.registry) &&
        type == condition.type;
  }

  @Override
  public int hashCode() {

    return Objects.hash(registry, type, value);
  }

  public boolean isFulfill(Map<String, Integer> register) {
    register.putIfAbsent(registry, 0);
    return type.apply(register.get(registry), value);
  }
}
