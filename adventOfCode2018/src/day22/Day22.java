package day22;

import advent.AdventClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day22 implements AdventClass{

  @Override
  public String printFirst() {
    SporificaVirus sporificaVirus = new day22.part1.SporificaVirus(Input.parse(Input.INPUT, 501));
    sporificaVirus.burst(10_000);
    return "Infected nodes = " + sporificaVirus.getInfectionsCount();
  }

  @Override
  public String printSecond() {
    SporificaVirus sporificaVirus = new day22.part2.SporificaVirus(Input.parsePart2(Input.INPUT, 701));
    sporificaVirus.burst(10_000_000);
    return "Infected nodes = " + sporificaVirus.getInfectionsCount();
  }

  @Test
  public void testBurst() {
    String input = "..#\n#..\n...";
    SporificaVirus sporificaVirus = new day22.part1.SporificaVirus(Input.parse(input, 9));

    sporificaVirus.burst(7);

    assertEquals(5, sporificaVirus.getInfectionsCount());
  }

  @Test
  public void testBurst2() {
    String input = "..#\n#..\n...";
    SporificaVirus sporificaVirus = new day22.part1.SporificaVirus(Input.parse(input, 9));

    sporificaVirus.burst(70);

    assertEquals(41, sporificaVirus.getInfectionsCount());
  }

  @Test
  public void testBurst3() {
    String input = "..#\n#..\n...";
    SporificaVirus sporificaVirus = new day22.part2.SporificaVirus(Input.parsePart2(input, 21));

    sporificaVirus.burst(100);

    assertEquals(26, sporificaVirus.getInfectionsCount());
  }
}
