package day20;

import java.util.Objects;

public class Position {

  private long x;
  private long y;
  private long z;

  public Position(long x, long y, long z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public long getX() {
    return x;
  }

  public long getY() {
    return y;
  }

  public long getZ() {
    return z;
  }

  public void inc(Velocity velocity) {
    x += velocity.getX();
    y += velocity.getY();
    z += velocity.getZ();
  }

  public long getDistance() {
    return Math.abs(x) + Math.abs(y) + Math.abs(z);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Position position = (Position) o;
    return x == position.x &&
        y == position.y &&
        z == position.z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }
}
