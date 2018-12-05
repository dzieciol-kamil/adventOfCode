package day6;

import advent.AdventClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Day6 implements AdventClass{
  private static final String INPUT = "4\t10\t4\t1\t8\t4\t9\t14\t5\t1\t14\t15\t0\t15\t3\t5";
  private static final List<Integer> INPUT_LIST = getListFromString(INPUT);

  private static List<Integer> getListFromString(String input) {
    return Arrays.stream(input.split("\t"))
                 .map(Integer::parseInt)
                 .collect(Collectors.toList());
  }

  @Override
  public String printFirst() {
    return "Redistribution cycles = " + calcCycles(INPUT_LIST);
  }

  @Override
  public String printSecond() {
    return "Redistribution cycles second time = " + cylcSecondCycle(INPUT_LIST);
  }

  private int calcCycles(List<Integer> input) {
    String state = getState(input);
    List<String> seenList = new ArrayList<>();
    while (!seenList.contains(state)) {
      seenList.add(state);
      input = distributeBlocks(input);
      state = getState(input);
    }
    return seenList.size();
  }

  private int cylcSecondCycle(List<Integer> input) {
    List<Integer> memory = input;
    String state = "";
    String startState = getState(INPUT_LIST);
    int cycles = 0;
    while (!state.equals(startState)) {
      memory = distributeBlocks(memory);
      state = getState(memory);
      cycles++;
    }
    return cycles;
  }

  private String getState(List<Integer> inputList1) {
    return inputList1.stream().map(String::valueOf).collect(Collectors.joining("-"));
  }

  private List<Integer> distributeBlocks(List<Integer> memory) {
    Integer max = memory.stream().max(Comparator.naturalOrder()).get();
    int position = memory.indexOf(max);
    memory.set(position, 0);
    for (int i = 0; i < max; i++) {
      position++;
      if (position > memory.size() - 1) {
        position = 0;
      }
      memory.set(position, memory.get(position) + 1 );
    }
    return memory;
  }

  @Test
  public void testFirst() {
    assertEquals(5, calcCycles(Arrays.asList(0,2,7,0)));
  }
}
