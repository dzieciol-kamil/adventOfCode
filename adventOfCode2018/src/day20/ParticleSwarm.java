package day20;

import java.util.Collections;
import java.util.List;

public class ParticleSwarm {

  private List<Point> points;

  public ParticleSwarm(List<Point> points) {
    this.points = points;
  }

  public int findClosest(int rounds) {
    for (int i = 0; i < rounds; i++) {
      points.forEach(Point::move);
    }

    return findClosest();
  }

  public int leftAfterCollisions(int rounds) {
    for (int i = 0; i < rounds; i++) {
      points.forEach(Point::move);
      points.removeIf(point -> Collections.frequency(points, point) > 1);
    }

    return points.size();
  }

  private int findClosest() {
    long minDistance = points.get(0).distance();
    int pos = 0;
    for (int i = 1; i < points.size(); i++) {
      long distance = points.get(i).distance();
      if (minDistance > distance) {
        minDistance = distance;
        pos = i;
      }
    }
    return pos;
  }
}
