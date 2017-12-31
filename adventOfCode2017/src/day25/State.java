package day25;

import java.util.LinkedList;

public class State {
  private final Integer zeroValue;
  private final Integer zeroPosition;
  private final Character zeroState;
  private final Integer oneValue;
  private final Integer onePosition;
  private final Character oneState;

  public State(Integer zeroValue,
               Integer zeroPosition,
               Character zeroState,
               Integer oneValue,
               Integer onePosition,
               Character oneState) {
    this.zeroValue = zeroValue;
    this.zeroPosition = zeroPosition;
    this.zeroState = zeroState;
    this.oneValue = oneValue;
    this.onePosition = onePosition;
    this.oneState = oneState;
  }

  public NextMove execute(LinkedList<Integer> list, Integer position) {
    if (position < 0) {
      list.addFirst(0);
      position = 0;
    }

    if (position >= list.size()) {
      position = list.size();
      list.addLast(0);
    }

    if (list.get(position) == 0) {
      list.set(position, zeroValue);
      return new NextMove(zeroState, position + zeroPosition);
    } else {
      list.set(position, oneValue);
      return new NextMove(oneState, position + onePosition);
    }
  }
}
