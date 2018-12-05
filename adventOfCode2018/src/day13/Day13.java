package day13;

import advent.AdventClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Day13 implements AdventClass{

  private final Map<Integer, Integer> input;
  private final Firewall firewall;
  private String testInput = "0: 3\n"
                           + "1: 2\n"
                           + "4: 4\n"
                           + "6: 4";

  public Day13() {
    input = parseInput(Input.INPUT);
    firewall = new Firewall(input);
  }

  @Override
  public String printFirst() {
    return "Severity for this firewall is = " + firewall.getSeverity();
  }

  @Override
  public String printSecond() {
    return "Fewest number of picoseconds is = " + firewall.findDelayNumber();
  }

  @Test
  public void testSeverity() {
    Firewall firewall = new Firewall(parseInput(testInput));
    assertEquals(24, firewall.getSeverity());
  }

  @Test
  public void testDelay() {
    Firewall firewall = new Firewall(parseInput(testInput));
    assertEquals(10, firewall.findDelayNumber());
  }

  private Map<Integer, Integer> parseInput(String input) {
    Map<Integer, Integer> result = new HashMap<>();

    for (String s : Arrays.asList(input.split("\n"))) {
      List<Integer> keyValue = Arrays.stream(s.split(":"))
                                     .map(String::trim)
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());
      result.put(keyValue.get(0), keyValue.get(1));
    }
    return result;
  }
}
