package day24;

import advent.AdventClass;

public class Day24 implements AdventClass {

  private final BridgeBuilder bridgeBuilder;

  public Day24() {
    bridgeBuilder = new BridgeBuilder(Input.parse(Input.INPUT));
    bridgeBuilder.build();
  }

  @Override
  public String printFirst() {
    return "Strongest bridge " + bridgeBuilder.getStrongest();
  }

  @Override
  public String printSecond() {
    return "Longest bridge " + bridgeBuilder.getLongestStrenght();
  }
}
