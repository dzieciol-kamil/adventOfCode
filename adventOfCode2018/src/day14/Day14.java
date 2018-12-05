package day14;

import advent.AdventClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day14 implements AdventClass {
  private final String INPUT = "ugkiagan";

  public Day14() {
  }

  @Override
  public String printSecond() {
    Disk disk = new Disk(INPUT);
    return "Regions = " + disk.getRegionsCount();
  }

  @Override
  public String printFirst() {
    Disk disk = new Disk(INPUT);
    return "Used squares = " + disk.getUsedSquares();
  }

  @Test
  public void testUsedSquares() {
    Disk disk = new Disk("ugkiagan");
    assertEquals(8292, disk.getUsedSquares());
  }
}
