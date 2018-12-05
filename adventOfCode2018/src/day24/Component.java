package day24;

import java.util.Arrays;
import java.util.List;

public class Component {

  private final int first;
  private final int second;

  public Component(int first, int second) {
    this.first = first;
    this.second = second;
  }

  public int getFirst() {
    return first;
  }

  public int getSecond() {
    return second;
  }

  public List<Integer> getPins() {
    return Arrays.asList(first, second);
  }

  public int getStrength() {
    return first + second;
  }
}
