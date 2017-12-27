package day19;

import java.awt.Point;

public class Diagram {

  private String letters = "";
  private int steps = 0;

  public String getLetters() {
    return letters;
  }

  public int getSteps() {
    return steps;
  }

  public void follow(Character[][] grid) {
    Point point = new Point(0, findStart(grid[0]));
    Direction direction = Direction.DOWN;
    do {
      if (grid[point.x][point.y] == '+')
        direction = findNewDirection(grid, point, direction);

      if (grid[point.x][point.y] != '|' &&
          grid[point.x][point.y] != '-' &&
          grid[point.x][point.y] != '+')
        letters += grid[point.x][point.y];

      point = direction.move(point);
      steps++;

    } while (canMove(point, grid, direction));
  }

  private boolean canMove(Point point, Character[][] grid, Direction direction) {
    return grid[point.x][point.y] != ' ';
  }

  private Direction findNewDirection(Character[][] grid, Point point, Direction direction) {
    if (direction == Direction.DOWN || direction == Direction.UP)
      return checkLeftRight(grid, point);

    return checkUpDown(grid, point);
  }

  private Direction checkUpDown(Character[][] grid, Point point) {
    Point newPoint = Direction.DOWN.move(point);
    if (notOutsidetheGrid(grid, newPoint) &&
        validValue(grid[newPoint.x][newPoint.y]))
      return Direction.DOWN;

    return Direction.UP;
  }

  private boolean validValue(Character character) {
    return character == '|' || isLetter(character);
  }

  private boolean isLetter(Character character) {
    return character > 65 && character < 90;
  }

  private boolean notOutsidetheGrid(Character[][] grid, Point newPoint) {
    return (0 < newPoint.x) &&
        (newPoint.x < (grid.length - 1));
  }

  private Direction checkLeftRight(Character[][] grid, Point point) {
    Point newPoint = Direction.LEFT.move(point);
    if ((0 < newPoint.y) &&
        (newPoint.y < (grid[0].length - 1)) &&
        (grid[newPoint.x][newPoint.y] == '-'))
      return Direction.LEFT;

    return Direction.RIGHT;
  }

  private int findStart(Character[] characters) {
    for (int i = 0; i < characters.length; i++) {
      if (characters[i] == '|')
        return i;
    }
    return 0;
  }
}
