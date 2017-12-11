package day11;

public class Cube {
  private final int x;
  private final int y;
  private final int z;

  public Cube(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Cube add(Cube cube) {
    return new Cube(this.x + cube.getX(), this.y + cube.getY(), this.z + cube.getZ());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }
}
