package day13;

import java.util.Comparator;
import java.util.Map;

public class Firewall {

  private Map<Integer, Integer> input;
  private int size;
  private int severity = 0;

  public Firewall(Map<Integer, Integer> input) {
    this.input = input;
    size = this.input.keySet().stream().max(Comparator.naturalOrder()).get();
  }

  public int getSeverity() {
    go(0);
    return severity;
  }

  public int findDelayNumber() {
    int delay = 0;
    int caughtCount;
    do {
      delay++;
      caughtCount = go(delay);
    } while (caughtCount!=0);
    return delay;
  }

  private int go(int delay) {
    int caughtCount = 0;
    for (int step = 0; step < size + 1; step++) {
      Integer layerDepth = input.getOrDefault(step, 0);
      if (isCaught(step + delay, layerDepth)) {
        caughtCount++;
        severity+=step*layerDepth;
      }
    }
    return caughtCount;
  }

  private boolean isCaught(int step, Integer layerDepth) {
    if (layerDepth == 0)
      return false;

    int moveTime = layerDepth == 1 ? 2 : layerDepth * 2 - 2;
    return (step % moveTime) == 0;
  }
}
