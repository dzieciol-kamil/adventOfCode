package day22.part1;

public enum Node {
  INFECTED {
    @Override
    public Node infects() {
      return CLEAN;
    }

    @Override
    public boolean infected() {
      return true;
    }
  },
  CLEAN {
    @Override
    public Node infects() {
      return INFECTED;
    }

    @Override
    public boolean infected() {
      return false;
    }
  };

  public abstract Node infects();

  public abstract boolean infected();

  public static Node valueOf(Character value) {
    if (value == '#')
      return INFECTED;
    else
      return CLEAN;
  }
}
