package day20;

import advent.AdventClass;

import java.util.List;

public class Day20 implements AdventClass {

  @Override
  public String printFirst() {
    List<Point> points = Input.parse(Input.INPUT);
    ParticleSwarm particleSwarm = new ParticleSwarm(points);
    return "Closest particle " + particleSwarm.findClosest(1000);
  }

  @Override
  public String printSecond() {
    List<Point> points = Input.parse(Input.INPUT);
    ParticleSwarm particleSwarm = new ParticleSwarm(points);
    return "Left after collisions " + particleSwarm.leftAfterCollisions(1000);
  }
}
