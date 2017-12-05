import advent.AdventClass;
import day1.Day1;
import day2.Day2;
import day3.Day3;
import day4.Day4;
import day5.Day5;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {

  private static final List<AdventClass> adventClasses = Arrays.asList(
      new Day5(),
      new Day4(),
      new Day3(),
      new Day2(),
      new Day1()
  );

  public static void main(String[] args) {
    int i = adventClasses.size();
    adventClasses.forEach(adventClass -> {
      System.out.println(getSolution(adventClass::printFirst));
      System.out.println(getSolution(adventClass::printSecond));
    });
  }

  private static String getSolution(Supplier<String> method) {
    Instant start = Instant.now();
    String result = method.get();
    Instant end = Instant.now();
    Duration duration = Duration.between(start, end);
    return "Duration:" + duration + "\t\t" + result;
  }

}
