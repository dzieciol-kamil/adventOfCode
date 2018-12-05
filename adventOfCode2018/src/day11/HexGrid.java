package day11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HexGrid {

  private static final Map<String, Cube> directions = new HashMap() {{
    put("n",  new Cube( 0,  1, -1));
    put("ne", new Cube( 1,  0, -1));
    put("se", new Cube( 1, -1,  0));
    put("s",  new Cube( 0, -1,  1));
    put("sw", new Cube(-1,  0,  1));
    put("nw", new Cube(-1,  1,  0));
  }};

  private Cube cube = new Cube(0, 0, 0);
  private int furthest = 0;

  public void applyPath(List<String> inputList) {
    inputList.forEach(move -> {
      cube = cube.add(directions.get(move));
      furthest = furthest < getStepsCount() ? getStepsCount() : furthest;
    });
  }

  public int getStepsCount() {
    return Stream.of(Math.abs(cube.getX()), Math.abs(cube.getY()), Math.abs(cube.getZ()))
                 .max(Comparator.naturalOrder())
                 .get();
  }

  public int getFurthest() {
    return furthest;
  }
}
