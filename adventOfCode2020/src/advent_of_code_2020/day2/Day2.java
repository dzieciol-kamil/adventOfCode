package advent_of_code_2020.day2;

import advent_of_code_2020.advent.AdventClass;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static advent_of_code_2020.day2.Input.INPUT;
import static advent_of_code_2020.day2.Input.TEST_INPUT;

public class Day2 implements AdventClass {

  private static final List<String> SPLIT_INPUT = Arrays
      .stream(INPUT.split("\n"))
      .collect(Collectors.toList());


  @Override
  public String printFirst() {
    return "Valid passwords = " + SPLIT_INPUT
        .stream()
        .filter(line -> new Password(line).isValid())
        .count();
  }

  @Override
  public String printSecond() {
    return "Valid passwords = " + SPLIT_INPUT
        .stream()
        .filter(line -> new Password(line).isReallyValid())
        .count();
  }

  private static class Password {

    private static final Pattern PATTERN = Pattern.compile("([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)");
    private final int min;
    private final int max;
    private final char letter;
    private final String password;

    public Password(String line) {
      Matcher matcher = PATTERN.matcher(line);
      matcher.find();
      min = Integer.parseInt(matcher.group(1));
      max = Integer.parseInt(matcher.group(2));
      letter = matcher.group(3).charAt(0);
      password = matcher.group(4);
    }

    public boolean isValid() {
      long count = password.chars().mapToObj(c -> (char) c)
                         .filter(c -> c == letter)
                         .count();

      return count >= min && count <= max;
    }

    public boolean isReallyValid() {
      if (password.charAt(min-1) == letter && password.charAt(max-1) == letter)
        return false;

      return password.charAt(min-1) == letter || password.charAt(max-1) == letter;
    }
  }
}
