package day8;

import java.util.Map;
import java.util.Objects;

class Operation {

  private final String registry;
  private final OperationType type;
  private final int value;
  private final Condition condition;

  public Operation(String registry, OperationType type, int value, Condition condition) {
    this.registry = registry;
    this.type = type;
    this.value = value;
    this.condition = condition;
  }

  public String getRegistry() {
    return registry;
  }

  public OperationType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }

  public Condition getCondition() {
    return condition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Operation operation = (Operation) o;
    return value == operation.value &&
        Objects.equals(registry, operation.registry) &&
        type == operation.type &&
        Objects.equals(condition, operation.condition);
  }

  @Override
  public int hashCode() {

    return Objects.hash(registry, type, value, condition);
  }

  public void apply(Map<String, Integer> register) {
    register.putIfAbsent(registry, 0);
    if (condition.isFulfill(register))
      register.replace(registry, type.apply(register.get(registry), value));
  }
}
