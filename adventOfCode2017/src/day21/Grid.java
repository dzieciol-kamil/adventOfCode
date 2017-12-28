package day21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grid {

  private final Boolean[][] grid;

  public Grid(Boolean[][] grid) {
    this.grid = grid;
  }

  public Grid(List<Grid> grids) {
    int size = (int) Math.sqrt(grids.size());
    int gridPartLength = grids.get(0).grid.length;
    int newLength = gridPartLength * size;
    Boolean[][] newGrid = new Boolean[newLength][newLength];

    int listPosition = 0;

    for (int gridPosI = 0; gridPosI < size; gridPosI++) {
      for (int gridPosJ = 0; gridPosJ < size; gridPosJ++) {
        for (int i = 0; i < gridPartLength; i++) {
          for (int j = 0; j < gridPartLength; j++) {
            newGrid[gridPosI * gridPartLength + i][gridPosJ * gridPartLength + j] =
                grids.get(listPosition).grid[i][j];
          }
        }
        listPosition++;
      }
    }
    grid = newGrid;
  }

  public Boolean[][] getGrid() {
    return grid;
  }

  public int pixelsOn() {
    int result = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j])
          result++;
      }
    }
    return result;
  }

  public List<Grid> divide() {
    if (grid.length % 2 == 0)
      return div2();
    else
      return div3();
  }

  private List<Grid> div2() {
    return getGrids(2);
  }

  private List<Grid> div3() {
    return getGrids(3);
  }

  private List<Grid> getGrids(int size) {
    Boolean[][][][] breakGrid = new Boolean[grid.length / size][grid.length / size][size][size];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        breakGrid[i / size][j / size][i % size][j % size] = grid[i][j];
      }
    }
    List<Grid> result = new ArrayList<>();
    for (int i = 0; i < grid.length / size; i++) {
      for (int j = 0; j < grid.length / size; j++) {
        result.add(new Grid(breakGrid[i][j]));
      }
    }
    return result;
  }

  public Grid apply(Map<Grid, Grid> rules) {
    for (Grid generatedGrid : new VariationGenerator().generate(this)) {
      if (rules.containsKey(generatedGrid)) {
        Grid grid = rules.get(generatedGrid);
        return new Grid(grid.getGridCopy());
      }
    }
    throw new RuntimeException("Should not happend");
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        result.append(grid[i][j] ? "#" : ".");
      }
      result.append("/");
    }
    result.deleteCharAt(result.length()-1);
    return result.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    return this.toString().equals(o.toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  public Boolean[][] getGridCopy() {
    Boolean[][] copy = new Boolean[grid.length][];
    for(int i = 0; i < grid.length; i++)
      copy[i] = grid[i].clone();
    return copy;
  }
}
