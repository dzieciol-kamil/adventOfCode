package day10;

import advent.AdventClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements AdventClass{

  private static final String INPUT_STRING = "88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205";
  private static final List<Integer> INPUT = Arrays.stream(INPUT_STRING.split(","))
                                                   .map(Integer::parseInt)
                                                   .collect(Collectors.toList());

  private static final String INPUT_STRING_TEST = "3,4,1,5";
  private static final List<Integer> INPUT_TEST = Arrays.stream(INPUT_STRING_TEST.split(","))
                                                        .map(Integer::parseInt)
                                                        .collect(Collectors.toList());

  @Override
  public String printFirst() {
    TyingKnotHashing hashing = new TyingKnotHashing(256);
    hashing.hash(INPUT);
    return "Multiply of the two first number = " + hashing.getCheckNumber(); //11375
  }

  @Override
  public String printSecond() {
    String stringInput = "1,2,3";
    TyingKnotHashing hashing = new TyingKnotHashing(256);
    hash(hashing, INPUT_STRING);
    return "Knot hash value = " + hashing.getKnotHash();
  }

  private void hash(TyingKnotHashing hashing, String stringInput) {
    List<Integer> input = convertInputToAsciiCode(stringInput);
    input.addAll(Arrays.asList(17, 31, 73, 47, 23));
    for (int i = 0; i < 64; i++) {
      hashing.hash(input);
    }
  }

  private List<Integer> convertInputToAsciiCode(String input) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      result.add((int) input.charAt(i));
    }
    return result;
  }
}
