package day20;

public class Velocity {

  private long x;
  private long y;
  private long z;

  public Velocity(long x, long y, long z) {
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

  public void inc(Acceleration acceleration) {
    x += acceleration.getX();
    y += acceleration.getY();
    z += acceleration.getZ();
  }
}
