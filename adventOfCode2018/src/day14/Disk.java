package day14;

import day10.TyingKnotHashing;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Disk {
  private final Boolean[][] grid = new Boolean[128][128];
  private final Integer[][] gridRegions = new Integer[128][128];

  public Disk(String input) {
    for (int i = 0; i < 128; i++) {
      String rowInput = input + "-" + i;
      String knotHash = hash(rowInput);
      Boolean[] row = getRowBinary(knotHash);
      grid[i] = row;
    }
  }

  private Boolean[] getRowBinary(String knotHash) {
    Boolean[] row = new Boolean[128];
    String bits = new BigInteger(knotHash, 16).toString(2);
    bits = IntStream.range(0, 128 - bits.length())
                    .mapToObj(i -> "0")
                    .collect(Collectors.joining("")) + bits;
    char[] chars = bits.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      row[i] = chars[i] !='0';
    }
    return row;
  }

  private String hash(String stringInput) {
    TyingKnotHashing knotHashing = new TyingKnotHashing(256);
    List<Integer> input = convertInputToAsciiCode(stringInput);
    input.addAll(Arrays.asList(17, 31, 73, 47, 23));
    for (int i = 0; i < 64; i++) {
      knotHashing.hash(input);
    }
    return knotHashing.getKnotHash();
  }

  private List<Integer> convertInputToAsciiCode(String input) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      result.add((int) input.charAt(i));
    }
    return result;
  }

  public int getUsedSquares() {
    return Arrays.stream(grid)
                 .mapToInt(rows -> (int) Arrays.stream(rows).filter(used -> used).count())
                 .sum();
  }

  public int getRegionsCount() {
    int regionCount = 0;
    for (int i = 0; i < 128; i++) {
      for (int j = 0; j < 128; j++) {
        if (grid[i][j] && gridRegions[i][j] == null)
          fillRegion(i,j,++regionCount);
      }
    }
    return regionCount;
  }

  private void fillRegion(int i, int j, int number) {
    if (gridRegions[i][j] != null)
      return;

    gridRegions[i][j] = number;

    if (i+1 < grid.length && grid[i+1][j])
      fillRegion(i+1, j, number);
    if (j+1 < grid.length && grid[i][j+1])
      fillRegion(i, j+1, number);
    if (i-1 >= 0 && grid[i-1][j])
      fillRegion(i-1, j, number);
    if (j-1 >= 0 && grid[i][j-1])
      fillRegion(i, j-1, number);
  }
}
