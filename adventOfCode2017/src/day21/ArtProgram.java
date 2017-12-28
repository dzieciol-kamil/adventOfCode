package day21;

import java.util.List;
import java.util.Map;

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
    grids.forEach(dividedGrids -> dividedGrids.apply(rules));
    grid = new Grid(grids);
  }

  public int pixelsOn() {
    return grid.pixelsOn();
  }
}
