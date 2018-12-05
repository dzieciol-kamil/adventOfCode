package day20;

import java.util.Objects;

public class Point {

  private final Position position;
  private final Velocity velocity;
  private final Acceleration acceleration;

  public Point(Position position, Velocity velocity, Acceleration acceleration) {
    this.position = position;
    this.velocity = velocity;
    this.acceleration = acceleration;
  }

  public void move() {
    velocity.inc(acceleration);
    position.inc(velocity);
  }

  public long distance() {
    return position.getDistance();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Point point = (Point) o;
    return Objects.equals(position, point.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position);
  }
}
