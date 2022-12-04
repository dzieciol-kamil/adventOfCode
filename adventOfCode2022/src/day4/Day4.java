package day4;

import advent.AdventClass;

import static day4.Input.INPUT_LIST;
import static java.lang.Integer.parseInt;

public class Day4 implements AdventClass {

  @Override
  public String printFirst() {
    return "Count contains= " + INPUT_LIST.stream().map(Pair::new).filter(Pair::isContains).count();
  }

  @Override
  public String printSecond() {
    return "Count overlaps= " + INPUT_LIST.stream().map(Pair::new).filter(Pair::isOverlap).count();
  }

  private static class Pair {

    private static final int BEGINING = 0;
    private static final int END = 1;
    private final String[] elf1Sections;
    private final String[] elf2Sections;

    private Pair(String line) {
      var sections = line.split(",");
      var elf1 = sections[0];
      var elf2 = sections[1];

      elf1Sections = elf1.split("-");
      elf2Sections = elf2.split("-");
    }

    boolean isContains() {
      return (parseInt(elf1Sections[BEGINING]) <= parseInt(elf2Sections[BEGINING])
              && parseInt(elf1Sections[END]) >= parseInt(elf2Sections[END]))
          || (parseInt(elf1Sections[BEGINING]) >= parseInt(elf2Sections[BEGINING])
              && parseInt(elf1Sections[END]) <= parseInt(elf2Sections[END]));
    }

    boolean isOverlap() {
      return parseInt(elf1Sections[BEGINING]) <= parseInt(elf2Sections[END])
          && parseInt(elf2Sections[BEGINING]) <= parseInt(elf1Sections[END]);
    }
  }
}
