package day17;

import advent.AdventClass;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day17 implements AdventClass{

  private static final int INPUT = 303;

  @Override
  public String printFirst() {
    Spinlock spinlock = new Spinlock(2017);
    spinlock.spin(INPUT);
    return "Next value at a position after 2017 = " + spinlock.getAfterActualPosition();
  }

  @Override
  public String printSecond() {
    Spinlock spinlock = new Spinlock(50_000_000);
    spinlock.fakeSpin(INPUT);
    return "Next value at a position after 2017 = " + spinlock.getAfterZeroValue();
  }

  @Test
  public void testSpinlock() {
    checkSpinlock(1, 0, 1);
    checkSpinlock(2, 1, 1);
    checkSpinlock(3, 1, 2);
    checkSpinlock(4, 3, 2);
    checkSpinlock(5, 2, 1);
    checkSpinlock(6, 1, 5);
    checkSpinlock(7, 2, 2);
    checkSpinlock(8, 6, 6);
    checkSpinlock(9, 5, 1);
    checkSpinlock(2017, 638, 1530);
  }

  private void checkSpinlock(int spins, int valueExpected, int posExplected) {
    Spinlock spinlock = new Spinlock(spins);
    spinlock.spin(3);
    assertEquals(valueExpected, spinlock.getAfterActualPosition());
    assertEquals(posExplected, spinlock.getPosition());
  }
}
