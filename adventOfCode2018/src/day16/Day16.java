package day16;

import advent.AdventClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day16 implements AdventClass {

  private List<String> INPUT = Arrays.asList(Input.INPUT.split(","));

  @Override
  public String printFirst() {
    Dancing dancing = new Dancing(16);
    dancing.dance(INPUT, 1);
    return "Result of a dance = " + dancing.getPrograms();
  }

  @Override
  public String printSecond() {
    Dancing dancing = new Dancing(16);
    dancing.dance(INPUT, 1_000_000_000);
    return "Result of a dance after a one bilion times = " + dancing.getPrograms();
  }

  @Test
  public void testDancing() {
    Dancing testDancing = new Dancing(5);
    testDancing.dance(Arrays.asList("s1", "x3/4", "pe/b"), 1);
    assertEquals("baedc", testDancing.getPrograms());
  }
}
