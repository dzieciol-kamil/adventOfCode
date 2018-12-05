package day9;

import advent.AdventClass;


public class Day9 implements AdventClass {

  private final Parser parser;

  public Day9() {
    parser = new Parser();
    parser.parse(Input.INPUT);
  }

  @Override
  public String printFirst() {
    return "Groups count = " + parser.getScore();
  }

  @Override
  public String printSecond() {
    return "Garbage count = " + parser.getGarbageCount();
  }
}
