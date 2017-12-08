package day8;

public enum ConditionType {
  GT(">") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue > conditionValue;
    }
  },
  LT("<") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue < conditionValue;
    }
  },
  GE(">=") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue >= conditionValue;
    }
  },
  LE("<=") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue <= conditionValue;
    }
  },
  EQ("==") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue == conditionValue;
    }
  },
  NE("!=") {
    @Override
    public boolean apply(int registryValue, int conditionValue) {
      return registryValue != conditionValue;
    }
  };

  private final String value;
  public abstract boolean apply(int registryValue, int conditionValue);

  ConditionType(String value) {
    this.value = value;
  }

  public static ConditionType of(String text) {
    for (ConditionType type : ConditionType.values()) {
      if (type.value.equalsIgnoreCase(text)) {
        return type;
      }
    }
    return null;
  }
}
