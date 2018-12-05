package day12;

import advent.AdventClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Day12 implements AdventClass {
  private final Graph graph;

  public Day12() {
    graph = new Graph(parseInput(Input.INPUT));
  }

  private Map<Integer, List<Integer>> parseInput(String input) {
    Map<Integer, List<Integer>> result = new HashMap<>();
    for (String line : Arrays.asList(input.split("\n"))) {
      List<String> keyValue = Arrays.asList(line.split(" <-> "));
      Integer key = Integer.parseInt(keyValue.get(0).trim());
      List<Integer> values = Arrays.stream(keyValue.get(1).split(","))
                                   .map(String::trim)
                                   .map(Integer::parseInt)
                                   .collect(Collectors.toList());
      result.put(key, values);
    }
    return result;
  }


  @Override
  public String printFirst() {
    return "Programs connected to 0 = " + graph.calcConnectionsTo(0);
  }

  @Override
  public String printSecond() {
    return "Groups in input = " + graph.calcGroups(0);
  }
  
  @Test
  public void testCalcConnectionsTo() {
    String input =
        "0 <-> 2\n"
      + "1 <-> 1\n"
      + "2 <-> 0, 3, 4\n"
      + "3 <-> 2, 4\n"
      + "4 <-> 2, 3, 6\n"
      + "5 <-> 6\n"
      + "6 <-> 4, 5";
    checkConnections(input, 6);
  }

  private void checkConnections(String input, int expected) {
    Map<Integer, List<Integer>> integerListMap = parseInput(input);
    Graph graph = new Graph(integerListMap);
    assertEquals(expected, graph.calcConnectionsTo(0) );
  }
}
