package day1;

import advent.AdventClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static day1.Input.INPUT;

public class Day1 implements AdventClass {

  private static final String[] SPLITTED_INPUT = INPUT.split("\n");

  @Override
  public String printFirst() {
    return "Max calories=" + getMax(generateElfs());
  }

  private Long getMax(List<Elf> elfs) {
    return elfs.stream().max(Comparator.comparingLong(Elf::getCalories)).get().getCalories();
  }

  private List<Elf> generateElfs() {
    ArrayList<String> food = new ArrayList<String>();
    AtomicInteger counter = new AtomicInteger(1);
    ArrayList<Elf> result = new ArrayList<Elf>();
    for (String s : SPLITTED_INPUT) {
      if (s.isEmpty()) {
        result.add(new Elf(counter.getAndIncrement(), food));
        food.clear();
      } else {
        food.add(s);
      }
    }
    return result;
  }

  @Override
  public String printSecond() {
    return "Max 3 elfs calories sum =" + get3Max(generateElfs());
  }

  private Long get3Max(List<Elf> elfs) {
    return elfs.stream().map(Elf::getCalories).sorted(Comparator.reverseOrder()).limit(3)
        .mapToLong(Long::longValue).sum();
  }

  private static class Elf{
    private final int index;
    private final List<Integer> food;

    public Elf(int index, List<String> food) {
      this.index = index;
      this.food = food.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    long getCalories() {
      return food.stream().mapToLong(Integer::longValue).sum();
    }
  }
}
