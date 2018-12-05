package day24;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

  private final List<Component> components;

  public Bridge(List<Component> usedCopy) {
    components = new ArrayList<>(usedCopy);
  }

  public int getStrength() {
    int strength = 0;
    for (Component component : components) {
      strength += component.getStrength();
    }
    return strength;
  }

  public int getLenght() {
    return components.size();
  }
}
