package day6;

import advent.AdventClass;

import java.util.HashSet;

public class Day6 implements AdventClass {

  private static final String INPUT = Input.INPUT;

  @Override
  public String printFirst() {
    return "Marked starts at = " + calculateMarkerStart(INPUT, 4);
  }

  @Override
  public String printSecond() {
    return "Message starts at = " + calculateMarkerStart(INPUT, 14);
  }

  private int calculateMarkerStart(String input, int length) {
    for (int i = 0; i < input.length() - length - 1; i++) {
      if (allDifferent(input.substring(i, i + length))) {
        return i + length;
      }
    }
    return 0;
  }

  private boolean allDifferent(String substring) {
    HashSet<Character> characters = new HashSet<>();
    for (int i = 0; i < substring.length(); i++) {
      characters.add(substring.charAt(i));
    }
    return characters.size() == substring.length();
  }
}
