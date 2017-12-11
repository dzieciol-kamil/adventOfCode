package day11;

import advent.AdventClass;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day11 implements AdventClass{
  public final static List<String> INPUT_LIST = Arrays.asList(Input.INPUT.split(","));
  private final HexGrid hexGrid;

  public Day11() {
    hexGrid = new HexGrid();
    hexGrid.applyPath(INPUT_LIST);
  }

  @Override
  public String printFirst() {
    return "Steps = " + hexGrid.getStepsCount();
  }

  @Override
  public String printSecond() {
    return "Furthest = " + hexGrid.getFurthest();
  }


  @Test
  public void testStesCount() {
    checkHexGrid("ne,ne,ne", 3);
    checkHexGrid("ne,ne,sw,sw", 0);
    checkHexGrid("ne,ne,s,s", 2);
    checkHexGrid("se,sw,se,sw,sw", 3);
  }

  private void checkHexGrid(String input, int expected) {
    List<String> inputList = Arrays.asList(input.split(","));
    HexGrid hexGrid = new HexGrid();
    hexGrid.applyPath(inputList);
    assertEquals(expected, hexGrid.getStepsCount());
  }
}
