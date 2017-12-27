package day19;

import advent.AdventClass;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day19 implements AdventClass{

  private final Diagram diagram;

  public Day19() {
    Character[][] grid = Input.parse(Input.INPUT);
    diagram = new Diagram();
    diagram.follow(grid);
  }

  @Override
  public String printFirst() {
    return "Packet will see a letters = " + diagram.getLetters();
  }

  @Override
  public String printSecond() {
    return "Packet will need a " + diagram.getSteps() + " steps.";
  }

  @Test
  public void testDiagram() {
    Diagram diagram = new Diagram();
    String testInput = "     |          \n"
                     + "     |  +--+    \n"
                     + "     A  |  C    \n"
                     + " F---|----E|--+ \n"
                     + "     |  |  |  D \n"
                     + "     +B-+  +--+ \n";

    diagram.follow(Input.parse(testInput));

    assertEquals("ABCDEF", diagram.getLetters());
    assertEquals(38, diagram.getSteps());
  }
}
