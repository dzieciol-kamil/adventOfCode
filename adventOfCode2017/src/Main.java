import advent.AdventClass;
import day1.Day1;
import day10.Day10;
import day11.Day11;
import day12.Day12;
import day13.Day13;
import day14.Day14;
import day15.Day15;
import day16.Day16;
import day17.Day17;
import day18.Day18;
import day19.Day19;
import day2.Day2;
import day20.Day20;
import day21.Day21;
import day22.Day22;
import day23.Day23;
import day24.Day24;
import day25.Day25;
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

  private static final boolean ALL = true;

  private static final List<AdventClass> adventClasses = Arrays.asList(
      new Day25(),
      new Day24(),
      new Day23(),
      new Day22(),
      new Day21(),
      new Day20(),
      new Day19(),
      new Day18(),
      new Day17(),
      new Day16(),
      new Day15(),
      new Day14(),
      new Day13(),
      new Day12(),
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
      if (!ALL)
        break;
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
