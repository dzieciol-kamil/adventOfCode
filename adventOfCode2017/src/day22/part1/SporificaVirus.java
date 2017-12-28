package day22.part1;

import java.awt.Point;

public class SporificaVirus implements day22.SporificaVirus{

  private final Node[][] parsedInput;
  Point actualPosition;
  Direction direction;
  int infections = 0;

  public SporificaVirus(Node[][] parsedInput) {
    this.parsedInput = parsedInput;
    int middle = (parsedInput.length / 2);
    actualPosition = new Point(middle, middle);
    direction = Direction.UP;
  }

  @Override
  public int getInfectionsCount() {
    return infections;
  }

  @Override
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
