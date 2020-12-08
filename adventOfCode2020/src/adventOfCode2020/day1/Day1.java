package adventOfCode2020.day1;

import adventOfCode2020.advent.AdventClass;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static adventOfCode2020.day1.Input.INPUT;

public class Day1 implements AdventClass {

  private static final List<Integer> SPLIT_INPUT = Arrays
      .stream(INPUT.split("\n"))
      .map(Integer::parseInt)
      .collect(Collectors.toList());


  @Override
  public String printFirst() {
    return "Multiplication of two = " + findAndMultiply();
  }

  @Override
  public String printSecond() {
    return "Multiplication of tree = " + findThreeAndMultiply();
  }

  private Integer findAndMultiply() {
    for (Integer first : SPLIT_INPUT) {
      Optional<Integer> result = SPLIT_INPUT
          .stream()
          .filter(second -> first + second == 2020)
          .findFirst()
          .map(second -> first * second);
      if (result.isPresent())
        return result.get();
    }
    return 0;
  }

  private Integer findThreeAndMultiply() {
    for (Integer first : SPLIT_INPUT) {
      for (Integer second : SPLIT_INPUT) {
        Optional<Integer> result = SPLIT_INPUT
            .stream()
            .filter(third -> first + second + third == 2020)
            .findFirst()
            .map(third -> first * second * third);
        if (result.isPresent())
          return result.get();
      }
    }
    return 0;
  }

}
