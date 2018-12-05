package day25;

public class NextMove {

  private final Character state;
  private final int position;

  public NextMove(Character state, int position) {
    this.state = state;
    this.position = position;
  }

  public Character getState() {
    return state;
  }

  public int getPosition() {
    return position;
  }
}
