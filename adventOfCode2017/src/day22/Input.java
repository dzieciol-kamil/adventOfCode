package day22;

import java.util.Arrays;

public class Input {
  public static final String INPUT =
      "..######.###...######...#\n"
    + ".##..##.#....#..##.#....#\n"
    + ".##.#....###..##.###.#.#.\n"
    + "#.#.###.#####.###.##.##.#\n"
    + ".###.#.#.###.####..##.###\n"
    + "..####.##..#.#.#####...##\n"
    + "....##.###..#.#..#...####\n"
    + ".#.##.##.#..##...##.###..\n"
    + ".######..#..#.#####....##\n"
    + "###.##.###.########...###\n"
    + ".#.#.#..#.##.#..###...#..\n"
    + ".#.##.#.####.#.#.....###.\n"
    + "##..###.###..##...#.##.##\n"
    + "##.#.##..#...##...#...###\n"
    + "##..#..###.#..##.#.#.#.#.\n"
    + ".##.#####..##....#.#.#..#\n"
    + "..#.######.##...#..#.##..\n"
    + "#.##...#.#....###.#.##.#.\n"
    + ".#..#.#.#..#.####..#.####\n"
    + ".##...##....##..#.#.###..\n"
    + "..##.#.#.##..##.#.#....#.\n"
    + "###.###.######.#.########\n"
    + "..#.####.#.#.##..####...#\n"
    + "#.##..#.#.####...#..#..##\n"
    + "###.###.#..##..#.###....#";

  public static day22.part1.Node[][] parse(String input, int size) {
    day22.part1.Node[][] result = new day22.part1.Node[size][size];
    for (day22.part1.Node[] nodes : result) {
      Arrays.fill(nodes, day22.part1.Node.CLEAN);
    }
    int middle = size / 2;
    String[] lines = input.split("\n");
    int inputStartPos = middle - (lines.length / 2);
    int inputEndPos = middle + (lines.length / 2);
    for (int i = inputStartPos; i <= inputEndPos; i++) {
      for (int j = inputStartPos; j <= inputEndPos; j++) {
        day22.part1.Node node = day22.part1.Node.valueOf(lines[i - inputStartPos].charAt(j - inputStartPos));
        result[i][j] = node;
      }
    }
    return result;
  }

  public static day22.part2.Node[][] parsePart2(String input, int size) {
    day22.part2.Node[][] result = new day22.part2.Node[size][size];
    for (day22.part2.Node[] nodes : result) {
      Arrays.fill(nodes, day22.part2.Node.CLEAN);
    }
    int middle = size / 2;
    String[] lines = input.split("\n");
    int inputStartPos = middle - (lines.length / 2);
    int inputEndPos = middle + (lines.length / 2);
    for (int i = inputStartPos; i <= inputEndPos; i++) {
      for (int j = inputStartPos; j <= inputEndPos; j++) {
        day22.part2.Node node = day22.part2.Node.valueOf(lines[i - inputStartPos].charAt(j - inputStartPos));
        result[i][j] = node;
      }
    }
    return result;
  }

}
