package day2;

import advent.AdventClass;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static day2.Input.INPUT_LIST;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Day2 implements AdventClass {

  @Override
  public String printFirst() {
    return "Score=" + new Games(INPUT_LIST).calculateScore();
  }

  @Override
  public String printSecond() {
    return "Score=" + new Games(INPUT_LIST).calculateProperScore();
  }

  private static class Games {

    List<Game> games;

    Games(List<String> inputList) {
      games = inputList.stream().map(Game::new).collect(toList());
    }

    Long calculateScore() {
      return games.stream().map(Game::score).mapToLong(Integer::longValue).sum();
    }

    Long calculateProperScore() {
      return games.stream().map(Game::properScore).mapToLong(Integer::longValue).sum();
    }
  }

  private static class Game {
    private final RPS elf;
    private final RPS me;
    private final String meRaw;

    Game(String input) {
      String[] splitedInput = input.split(" ");

      elf = RPS.of(splitedInput[0]);
      meRaw = splitedInput[1];
      me = RPS.of(splitedInput[1]);
    }

    int score() {
      return me.getValue() + RpsResult.of(elf,me).getValue();
    }

    int properScore() {
      RpsResult result = RpsResult.of(meRaw);
      return result.getValue() + result.getMyMove(elf).getValue();
    }
  }

  private enum RPS {
    Rock (1),
    Paper (2),
    Scissors (3);

    final int value;

    RPS(int i) {
      value = i;
    }

    private int getValue() {
      return value;
    }

    static RPS of(String s) {
      if (asList("A", "X").contains(s))
        return Rock;
      if (asList("B", "Y").contains(s))
        return Paper;
      if (asList("C", "Z").contains(s))
        return Scissors;
      throw new NoSuchElementException(s);
    }
  }

  private enum RpsResult {
    Lost (0, RpsResult::ofLost),
    Draw (3, RpsResult::ofDraw),
    Win (6, RpsResult::ofWin);

    private static RPS ofWin(RPS rps) {
      if (rps == RPS.Paper)
        return RPS.Scissors;
      if (rps == RPS.Rock)
        return RPS.Paper;
      return RPS.Rock;
    }

    private static RPS ofDraw(RPS rps) {
      return rps;
    }

    private static RPS ofLost(RPS rps) {
      if (rps == RPS.Paper)
        return RPS.Rock;
      if (rps == RPS.Rock)
        return RPS.Scissors;
      return RPS.Paper;
    }

    final int value;
    final Function<RPS, RPS> reverse;

    RpsResult(int i, Function<RPS, RPS> function) {
      value = i;
      reverse = function;
    }

    private int getValue() {
      return value;
    }

    static RpsResult of(String result) {
      if (result.equals("X"))
        return Lost;
      if (result.equals("Y"))
        return Draw;
      return Win;
    }

    static RpsResult of(RPS elf, RPS me) {
      if (elf == RPS.Rock && me == RPS.Rock)
        return Draw;
      if (elf == RPS.Paper && me == RPS.Paper)
        return Draw;
      if (elf == RPS.Scissors && me == RPS.Scissors)
        return Draw;
      if (elf == RPS.Rock && me == RPS.Paper)
        return Win;
      if (elf == RPS.Paper && me == RPS.Scissors)
        return Win;
      if (elf == RPS.Scissors && me == RPS.Rock)
        return Win;
      return Lost;
    }

    RPS getMyMove(RPS elf) {
      return this.reverse.apply(elf);
    }
  }
}
