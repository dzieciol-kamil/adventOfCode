package day1;

import advent.AdventClass;

import java.util.ArrayList;
import java.util.List;

import static day1.Input.INPUT;

public class Day1 implements AdventClass {

  private static final String[] SPLITTED_INPUT = INPUT.split("\n");

  private Integer firstTwice = null;
  private List<Integer> frequencies = new ArrayList<>();
  private int result = 0;

  @Override
  public String printFirst() {
    return "Frequency=" + calculateFreq();
  }

  @Override
  public String printSecond() {
    result = 0;
    frequencies = new ArrayList<>();
    while (firstTwice == null)
      calculateFreq();
    return "First twice=" + firstTwice;
  }

  private int calculateFreq() {
    for (String change : SPLITTED_INPUT) {
      frequencies.add(result);
      if (frequencies.stream().distinct().count() != frequencies.size() && firstTwice == null)
        firstTwice = result;
      if (change.startsWith("+"))
        result += Integer.parseInt(change.substring(1));
      else
        result -= Integer.parseInt(change.substring(1));
    }
    return result;
  }
}
