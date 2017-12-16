package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dancing {

  private List<Character> programs = new ArrayList();
  private final int programsLength;
  private Map<String, Integer> programsDance = new HashMap<>();

  public Dancing(int i) {
    for (char c = 'a'; c < 'a' + i; c++) {
      this.programs.add(c);
    }
    programsLength = programs.size();
  }

  public String getPrograms() {
    return getProgramString(programs);
  }

  private String getProgramString(List<Character> characterList) {
    return characterList.stream().map(Object::toString).collect(Collectors.joining());
  }

  public void dance(List<String> input, int counts) {
    for (int i = 1; i <= counts; i++) {
      for (String s : input) {
        if (s.charAt(0) == 'x')
          exchange(s.substring(1, s.length()));
        if (s.charAt(0) == 's')
          spin(s.substring(1, s.length()));
        if (s.charAt(0) == 'p')
          partner(s.substring(1, s.length()));
      }
      String programString = getProgramString(programs);
      if (programsDance.containsKey(programString)) {
        setPrograms(i, counts, programString);
        break;
      }
      programsDance.put(programString, i);
    }
  }

  private void setPrograms(int present, int counts, String programString) {
    Integer previous = programsDance.get(programString);
    int period = present - previous;
    int rest = (counts % period);
    programsDance.forEach((key, value) -> isRightValue(key, value, previous + rest - 1));
  }

  private void isRightValue(String key, Integer value, int probablyValue) {
    if (value == probablyValue)
      for (int i = 0; i < programsLength; i++) {
        programs.set(i, key.charAt(i));
      }
  }

  private void partner(String substring) {
    String[] split = substring.split("/");
    replace(programs.indexOf(split[0].charAt(0)),
            programs.indexOf(split[1].charAt(0)));
  }

  private void exchange(String substring) {
    String[] split = substring.split("/");
    int firstPosition = Integer.parseInt(split[0]);
    int secondPosition = Integer.parseInt(split[1]);

    replace(firstPosition, secondPosition);
  }

  private void replace(int first, int second) {
    Character firstChar = programs.get(first);
    Character secondChar = programs.get(second);
    programs.set(first, secondChar);
    programs.set(second, firstChar);
  }

  private void spin(String substring) {
    int programsToSpin = Integer.parseInt(substring);
    for (int i = 0; i < programsToSpin; i++) {
      Character removed = programs.remove(programsLength - 1);
      programs.add(0, removed);
    }
  }
}
