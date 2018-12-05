package day21;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArtProgram {

  private Grid grid;
  private final Map<Grid, Grid> rules;

  public ArtProgram(Grid grid, Map<Grid, Grid> rules) {
    this.grid = grid;
    this.rules = rules;
  }

  public void art(int iteration) {
    for (int i = 0; i < iteration; i++) {
      art();
    }
  }

  private void art() {
    List<Grid> grids = grid.divide();
    List<Grid> enhancedGrids = grids.stream()
                                    .map(dividedGrids -> dividedGrids.apply(rules))
                                    .collect(Collectors.toList());
    grid = new Grid(enhancedGrids);
  }

  public int pixelsOn() {
    return grid.pixelsOn();
  }
}
