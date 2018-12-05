package day22.part2;

import java.awt.Point;

public class SporificaVirus implements day22.SporificaVirus {

  private final day22.part2.Node[][] parsedInput;
  Point actualPosition;
  day22.part2.Direction direction;
  int infections = 0;

  public SporificaVirus(day22.part2.Node[][] parsedInput) {
    this.parsedInput = parsedInput;
    int middle = (parsedInput.length / 2);
    actualPosition = new Point(middle, middle);
    direction = day22.part2.Direction.UP;
  }

  public int getInfectionsCount() {
    return infections;
  }

  public void burst(int count) {
    for (int i = 0; i < count; i++) {
      rotate();
      infect();
      move();
    }
  }

  private void rotate() {
    direction = this.direction.rotate(parsedInput[actualPosition.x][actualPosition.y]);
  }

  private void infect() {
    parsedInput[actualPosition.x][actualPosition.y] =
        parsedInput[actualPosition.x][actualPosition.y].infects();
    if (parsedInput[actualPosition.x][actualPosition.y].infected())
      infections++;
  }

  private void move() {
    actualPosition = direction.move(actualPosition);
  }
}
