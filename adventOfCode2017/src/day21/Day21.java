package day21;

import advent.AdventClass;

import java.util.Map;

public class Day21 implements AdventClass{

  @Override
  public String printFirst() {
    Map<Grid, Grid> rules = Input.parseInput(Input.INPUT);
    ArtProgram artProgram = new ArtProgram(Input.startGrid(), rules);
    artProgram.art(5);
    return "Pixels on after 5 iterations = " + artProgram.pixelsOn();
  }

  @Override
  public String printSecond() {
    Map<Grid, Grid> rules = Input.parseInput(Input.INPUT);
    ArtProgram artProgram = new ArtProgram(Input.startGrid(), rules);
    artProgram.art(18);
    return "Pixels on after 18 iterations = " + artProgram.pixelsOn();
  }
}
