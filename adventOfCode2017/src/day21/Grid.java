package day21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grid {

  private Boolean[][] grid;

  public Grid(Boolean[][] grid) {
    this.grid = grid;
  }

  public Grid(List<Grid> grids) {
    int size = (int) Math.sqrt(grids.size());
    int newLength = grids.get(0).grid.length * size;
    Boolean[][] newGrid = new Boolean[newLength][newLength];


    List<Grid> result = new ArrayList<>();
    for (int i = 0; i < grid.length / size; i++) {
      for (int j = 0; j < grid.length / size; j++) {
        result.add(new Grid(breakGrid[i][j]));
      }
    }

    for (int i = 0; i < newLength; i++) {
      for (int j = 0; j < newLength; j++) {
        newGrid[i][j] = grids
      }
    }

    newGrid[0][0] = grids.get(0).grid;
    newGrid[0][1] = grids.get(1).grid;
    newGrid[1][0] = grids.get(2).grid;
    newGrid[1][1] = grids.get(3).grid;

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

  public void apply(Map<Grid, Grid> rules) {

  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("\n");
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        result.append(grid[i][j] ? "#" : ".");
      }
      result.append("\n");
    }
    return result.toString();
  }
}
