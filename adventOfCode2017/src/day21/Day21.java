package day21;

import advent.AdventClass;

import java.util.Map;

public class Day21 implements AdventClass{

  @Override
  public String printFirst() {
    Map<Grid, Grid> rules = Input.parseInput(Input.INPUT);
    ArtProgram artProgram = new ArtProgram(Input.startGrid(), rules);
    artProgram.art(5);
    return "Pixels on = " + artProgram.pixelsOn();
  }

  @Override
  public String printSecond() {
    return null;
  }
}
