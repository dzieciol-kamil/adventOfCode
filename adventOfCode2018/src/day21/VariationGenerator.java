package day21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class VariationGenerator {

  public List<Grid> generate(Grid grid) {
    List<Grid> result = new ArrayList<>();
    result.add(grid);
    result.add(flipHorizontal(grid.getGridCopy()));
    result.add(flipVertical(grid.getGridCopy()));
    Grid rotate90 = rotate90(grid.getGridCopy());
    result.add(rotate90);
    result.add(flipHorizontal(rotate90.getGridCopy()));
    result.add(flipVertical(rotate90.getGridCopy()));
    Grid rotate180 = rotate90(rotate90.getGridCopy());
    result.add(flipHorizontal(rotate180.getGridCopy()));
    result.add(flipVertical(rotate180.getGridCopy()));
    result.add(rotate180);
    Grid rotate270 = rotate90(rotate180.getGridCopy());
    result.add(flipHorizontal(rotate270.getGridCopy()));
    result.add(flipVertical(rotate270.getGridCopy()));
    result.add(rotate270);
    return new ArrayList(new HashSet(result));
  }

  private Grid flipHorizontal(final Boolean[][] arrayGrid) {
    Boolean[] tmp = arrayGrid[0];
    arrayGrid[0] = arrayGrid[arrayGrid.length - 1];
    arrayGrid[arrayGrid.length - 1] = tmp;
    return new Grid(arrayGrid);
  }

  private Grid flipVertical(final Boolean[][] arrayGrid) {
    for (int i = 0; i < arrayGrid.length; i++) {
      Boolean tmp = arrayGrid[i][arrayGrid[i].length - 1];
      arrayGrid[i][arrayGrid[i].length - 1] = arrayGrid[i][0];
      arrayGrid[i][0] = tmp;
    }
    return new Grid(arrayGrid);
  }

  private Grid rotate90(final Boolean[][] arrayGrid) {
    int n = arrayGrid.length;
    Boolean tmp;
    for (int i = 0; i < n / 2; i++)
    {
      for (int j = i; j < n - i - 1; j++)
      {
        tmp = arrayGrid[i][j];
        arrayGrid[i][j] = arrayGrid[j][n-i-1];
        arrayGrid[j][n-i-1] = arrayGrid[n-i-1][n-j-1];
        arrayGrid[n-i-1][n-j-1] = arrayGrid[n-j-1][i];
        arrayGrid[n-j-1][i] = tmp;
      }
    }
    return new Grid(arrayGrid);
  }
}
