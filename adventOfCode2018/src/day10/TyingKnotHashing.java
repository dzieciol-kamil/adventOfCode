package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TyingKnotHashing {

  private final List<Integer> list;
  private int skipSize;
  private int position;

  public TyingKnotHashing(int size) {
    list = IntStream.range(0, size).boxed().collect(Collectors.toList());
  }

  public void hash(List<Integer> input) {
    for (Integer length : input) {
      process(position, length, skipSize);
      position = getValidPosition(position + skipSize + length);
      skipSize++;
    }
  }

  private int getValidPosition(int i) {
    while (i > list.size())
      i -= list.size();
    return i;
  }

  private void process(int position, int length, int skipSize) {
    List<Integer> sublist = getSublist(position, length);
    Collections.reverse(sublist);
    updateList(sublist, position);
  }

  private List<Integer> getSublist(int position, int length) {
    List<Integer> result = new ArrayList<>();
    if (position + length <= list.size())
      return new ArrayList<>(list.subList(position, position+length));

    result.addAll(list.subList(position, list.size()));
    result.addAll(list.subList(0, length - result.size()));
    return result;
  }

  private void updateList(List<Integer> sublist, int position) {
    for (int i = 0; i < sublist.size(); i++) {
      if (position + i >= list.size())
        position = -i;
      list.set(position + i, sublist.get(i));
    }
  }

  public int getCheckNumber() {
    return list.get(0) * list.get(1);
  }

  public String getKnotHash() {
    return String.join("", calculateXors().stream()
                                          .map(value -> String.format("%02X",value))
                                          .collect(Collectors.toList()));
  }

  private List<Integer> calculateXors() {
    List<Integer> result = new ArrayList<>();
    Integer xor = 0;
    for (int i = 0; i < list.size(); i++) {
      if (i % 16 == 0 && i != 0){
        result.add(xor);
        xor = 0;
      }
      xor = xor ^ list.get(i);
    }
    result.add(xor);
    return result;
  }
}
