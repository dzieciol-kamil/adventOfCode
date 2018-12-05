package day2;

import advent.AdventClass;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static day2.Input.INPUT_LIST;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Day2 implements AdventClass {

  @Override
  public String printFirst() {
    return "Checksum=" + new ChecksumCalculator(INPUT_LIST).getChecksum();
  }

  @Override
  public String printSecond() {
    return new SimilarIdFinder().getId(INPUT_LIST);
  }

  private class ChecksumCalculator {

    private int two = 0;
    private int three = 0;

    ChecksumCalculator(List<String> inputList) {
      inputList.forEach(this::checkId);
    }

    private void checkId(String id) {
      List<Character> characters = id.chars().mapToObj(c -> (char) c).collect(toList());
      if (characters.stream().distinct().count() == id.length())
        return;

      Map<Character, Long> countMap = characters
          .stream()
          .collect(groupingBy(letter -> letter, counting()));

      if (countMap.containsValue(2L))
        two++;

      if (countMap.containsValue(3L))
        three++;
    }

    long getChecksum() {
      return two * three;
    }
  }

  private class SimilarIdFinder {

    String getId(List<String> inputList) {
      for (String id : inputList) {
        Optional<String> similar = getSimilar(id, inputList);
        if (similar.isPresent()) {
          return calculateId(id, similar.get());
        }
      }
      return "";
    }

    private Optional<String> getSimilar(String id1, List<String> inputList) {
      return inputList.stream().filter(id -> areSimilar(id1, id)).findFirst();
    }

    private boolean areSimilar(String id1, String id2) {
      int result = 0;
      for(int i = 0; i < id1.length() && i < id2.length(); i++) {
        if(id1.charAt(i) == id2.charAt(i)){
          result++;
        }
      }
      return result == id1.length() - 1;
    }

    private String calculateId(String id1, String id2) {
      String result = "";
      for(int i = 0; i < id1.length() && i < id2.length(); i++) {
        if(id1.charAt(i) == id2.charAt(i)){
          result += id1.charAt(i);
        }
      }
      return result;
    }
  }
}
