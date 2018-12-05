package day25;

import java.util.LinkedList;
import java.util.Map;

public class TuringMachine {

  private final Map<Character, State> statesMap;
  private final LinkedList<Integer> list = new LinkedList<>();
  private Integer position = 0;
  private Character nextStep;
  private State state;

  public TuringMachine(Map<Character, State> statesMap) {
    this.statesMap = statesMap;
  }

  public void run(Character character, int steps) {
    nextStep = character;
    for (int i = 0; i < steps; i++) {
      state = statesMap.get(nextStep);
      NextMove nextMove = state.execute(list, position);
      nextStep = nextMove.getState();
      position = nextMove.getPosition();

    }
  }

  public int getChecksum() {
    int result = 0;
    for (Integer item : list) {
      result += item;
    }
    return result;
  }
}
