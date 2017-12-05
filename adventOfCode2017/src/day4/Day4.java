package day4;

import advent.AdventClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day4 implements AdventClass{

  private static final String INPUT = Input.INPUT;

  @Override
  public String printFirst() {
    return "Valid passphrases = " + calcValidPasses(INPUT, this::noDuplicateWords);
  }

  @Override
  public String printSecond() {
    return "Valid passphrases2 = " + calcValidPasses(INPUT, this::noRearrangebleWords);
  }

  private long calcValidPasses(String input, Function<String, Boolean> validateMethod) {
    return Arrays.stream(input.split("\n"))
                 .map(validateMethod)
                 .filter(valid -> valid)
                 .count();
  }

  private Boolean noDuplicateWords(String s) {
    List<String> stringList = Arrays.stream(s.split(" ")).collect(Collectors.toList());
    return stringList.size() == new HashSet<>(stringList).size();
  }

  private Boolean noRearrangebleWords(String s) {
    List<String> stringList = Arrays.stream(s.split(" ")).collect(Collectors.toList());
    Set<String> wordsSet = stringList.stream().map(this::sortLetters).collect(Collectors.toSet());
    return stringList.size() == wordsSet.size();
  }

  private String sortLetters(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
