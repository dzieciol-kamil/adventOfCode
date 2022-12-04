package day3;

import advent.AdventClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static day3.Input.INPUT_LIST;

public class Day3 implements AdventClass {

  @Override
  public String printFirst() {
    return "Sum=" + INPUT_LIST.stream().map(this::getValue).mapToLong(Integer::longValue).sum();
  }

  private Integer getValue(String s) {
    var first = convertToSet(s.substring(0, s.length() / 2).toCharArray());
    var second = convertToSet(s.substring(s.length() / 2).toCharArray());
    first.retainAll(second);

    var item = first.stream().findFirst().orElseThrow();
    return getValue(item);
  }

  private int getValue(Character item) {
    if (item < 95)
      return item - 38;
    else
      return item - 96;
  }

  private Set<Character> convertToSet(char[] chars) {
    var resultSet = new HashSet<Character>();

    for (char aChar : chars) {
      resultSet.add(aChar);
    }

    return resultSet;
  }

  @Override
  public String printSecond() {
    int pageSize = 3;

    return "Sum=" + IntStream
        .range(0, (INPUT_LIST.size() + pageSize - 1) / pageSize)
        .mapToObj(i -> INPUT_LIST
            .subList(i * pageSize, Math.min(pageSize * (i + 1), INPUT_LIST.size())))
        .map(this::calculate)
        .mapToLong(Integer::longValue)
        .sum();
  }

  private int calculate(List<String> strings) {
    var first = convertToSet(strings.get(0).toCharArray());
    var second = convertToSet(strings.get(1).toCharArray());
    var third = convertToSet(strings.get(2).toCharArray());

    first.retainAll(second);
    first.retainAll(third);

    Character character = first.stream().findFirst().orElseThrow();
    var multiply = count(strings.get(0), character)
        + count(strings.get(1), character)
        + count(strings.get(2), character);

    return getValue(character);
  }

  private int count(String s, Character character) {
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == character) {
        count++;
      }
    }
    return count;
  }
}
