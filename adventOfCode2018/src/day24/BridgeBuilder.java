package day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BridgeBuilder {

  private final List<Component> components;
  private final List<Bridge> bridges;

  public BridgeBuilder(List<Component> components) {
    this.components = components;
    bridges = new ArrayList<>();
  }

  public void build() {
    buildBridge(0, components, new ArrayList<>());
  }

  private void buildBridge(int pinValue,
                           List<Component> componentsLeft,
                           List<Component> used) {
    List<Component> componentsWithMatchingPin = getComponentsWithMatchingPin(pinValue,
                                                                             componentsLeft);
    if (componentsWithMatchingPin.isEmpty())
      bridges.add(new Bridge(used));

    for (Component component : componentsWithMatchingPin) {
      List<Component> usedCopy = new ArrayList<>(used);
      usedCopy.add(component);
      List<Component> componentsWithoutSelected = getComponentsLeft(componentsLeft, component);
      buildBridge(getSecondValue(component, pinValue), componentsWithoutSelected, usedCopy);
    }
  }

  private List<Component> getComponentsWithMatchingPin(int startingValue,
                                                       List<Component> componentsLeft) {
    return componentsLeft.stream()
                         .filter(component -> component.getPins().contains(startingValue))
                         .collect(Collectors.toList());
  }

  private List<Component> getComponentsLeft(List<Component> componentsLeft, Component component) {
    List<Component> result = new ArrayList<>(componentsLeft);
    result.remove(component);
    return result;
  }

  private int getSecondValue(Component component, int pinValue) {
    return component.getPins()
                    .stream()
                    .filter(value -> value != pinValue).findFirst()
                    .orElse(pinValue);
  }

  public int getStrongest() {
    return bridges.stream().max(Comparator.comparingInt(Bridge::getStrength)).get().getStrength();
  }

  public int getLongestStrenght() {
    int maxLenght = bridges.stream()
                           .max(Comparator.comparingInt(Bridge::getLenght))
                           .get()
                           .getLenght();
    return bridges.stream()
                  .filter(bridge->bridge.getLenght() == maxLenght)
                  .max(Comparator.comparingInt(Bridge::getStrength))
                  .get()
                  .getStrength();
  }
}
