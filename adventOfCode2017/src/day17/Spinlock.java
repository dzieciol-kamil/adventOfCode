package day17;

import java.util.ArrayList;
import java.util.List;

public class Spinlock {

  private final int maxCounter;
  private int position;
  private int zeroPosition;
  private List<Integer> buffer;
  private int nextValue;

  public Spinlock(int maxCounter) {
    this.maxCounter = maxCounter;
    zeroPosition = 0;
    buffer = new ArrayList<>();
    buffer.add(0);
    nextValue = 0;
  }

  public int getPosition() {
    return position;
  }

  public void spin(int input) {
    for (int counter = 1; counter <= maxCounter; counter++) {
      insertToBuffer(input, counter);
    }
  }

  public void fakeSpin(int input) {
    for (int counter =1; counter<=maxCounter; counter++) {
      fakeInsert(input, counter);
    }
  }

  private void fakeInsert(int input, int counter) {
    position += input;
    if (position >= counter)
      position = position % counter;
    if (++position == 1)
      nextValue = counter;
  }

  private void insertToBuffer(int input, int counter) {
    position += input;
    if (position >= buffer.size())
      position = position % buffer.size();

    buffer.add(++position, counter);
  }

  public int getAfterActualPosition() {
    if (position + 1 >= buffer.size())
      return buffer.get(0);
    else
      return buffer.get(position + 1);
  }

  public int getAfterZeroValue() {
    return nextValue;
  }
}
