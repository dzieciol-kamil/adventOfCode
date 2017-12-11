import advent.AdventClass;
import day1.Day1;
import day10.Day10;
import day11.Day11;
import day2.Day2;
import day3.Day3;
import day4.Day4;
import day5.Day5;
import day6.Day6;
import day7.Day7;
import day8.Day8;
import day9.Day9;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {

  private static final List<AdventClass> adventClasses = Arrays.asList(
      new Day11(),
      new Day10(),
      new Day9(),
      new Day8(),
      new Day7(),
      new Day6(),
      new Day5(),
      new Day4(),
      new Day3(),
      new Day2(),
      new Day1()
  );

  public static void main(String[] args) {
    int i = adventClasses.size();
    for (AdventClass adventClass : adventClasses) {
      System.out.println(String.format("Day %d", i));
      System.out.println(getSolution(adventClass::printFirst));
      System.out.println(getSolution(adventClass::printSecond));
      System.out.println();
      i--;
    }
  }

  private static String getSolution(Supplier<String> method) {
    Instant start = Instant.now();
    String result = method.get();
    Instant end = Instant.now();
    Duration duration = Duration.between(start, end);
    return String.format("%s\nDuration: %s", result, duration);
  }

}
