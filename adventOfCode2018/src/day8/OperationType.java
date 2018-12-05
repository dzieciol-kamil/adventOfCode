package day8;

public enum OperationType {
  INC("inc") {
    @Override
    public int apply(int registerValue, int operationValue) {
      return registerValue += operationValue;
    }
  },
  DEC("dec") {
    @Override
    public int apply(int registerValue, int operationValue) {
      return registerValue -= operationValue;
    }
  };

  private final String value;
  public abstract int apply(int registerValue, int operationValue);

  OperationType(String value) {
    this.value = value;
  }

  public static OperationType of(String text) {
    for (OperationType type : OperationType.values()) {
      if (type.value.equalsIgnoreCase(text)) {
        return type;
      }
    }
    return null;
  }
}
