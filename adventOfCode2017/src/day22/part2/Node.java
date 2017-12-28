package day22.part2;

public enum Node {
  CLEAN {
    @Override
    public Node infects() {
      return WEAKENED;
    }

    @Override
    public boolean infected() {
      return false;
    }
  },
  WEAKENED {
    @Override
    public Node infects() {
      return INFECTED;
    }

    @Override
    public boolean infected() {
      return false;
    }
  },
  INFECTED {
    @Override
    public Node infects() {
      return FLAGGED;
    }

    @Override
    public boolean infected() {
      return true;
    }
  },
  FLAGGED {
    @Override
    public Node infects() {
      return CLEAN;
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
