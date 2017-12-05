import advent.AdventClass;
import day1.Day1;
import day2.Day2;
import day3.Day3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Main {

  private static final List<AdventClass> adventClasses = Arrays.asList(
      new Day3(),
      new Day2(),
      new Day1()
  );

  public static void main(String[] args) {
    adventClasses.forEach(adventClass -> {
      System.out.println(adventClass.printFirst());
      System.out.println(adventClass.printSecond());
    });
  }

}
