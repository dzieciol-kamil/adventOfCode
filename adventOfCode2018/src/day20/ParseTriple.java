package day20;

public class ParseTriple {

  private long x;
  private long y;
  private long z;

  public ParseTriple(String value) {
    String[] splitedNameValues = value.split("=");
    String values = splitedNameValues[1].replace("<", "").replace(">", "");
    String[] splitedValues = values.split(",");
    x = Long.parseLong(splitedValues[0]);
    y = Long.parseLong(splitedValues[1]);
    z = Long.parseLong(splitedValues[2]);
  }

  public long getX() {
    return x;
  }

  public long getY() {
    return y;
  }

  public long getZ() {
    return z;
  }
}
