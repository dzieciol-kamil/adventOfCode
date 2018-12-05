package day3;

import advent.AdventClass;

import java.util.List;
import java.util.regex.Pattern;

import static day3.Input.INPUT_LIST;

public class Day3 implements AdventClass {


  private static final Pattern PATTERN = Pattern
      .compile(".*(\\d+).*(\\d+).*(\\d+).*(\\d+).*(\\d+)");

  @Override
  public String printFirst() {
    return "Overlaped size=" + new Area(INPUT_LIST).getOverlapSize();
  }

  @Override
  public String printSecond() {
    return "Not overlaped claim=" + new Area(INPUT_LIST).getNotOverlapedClaim();
  }


  private class Area {

    String[][] area = new String[2000][2000];
    private final List<String> input;

    public Area(List<String> input) {
      this.input = input;
      for (String s : input) {
        markArea(new Claim(s));
      }
    }

    private void markArea(Claim claim) {
      for(int i = claim.topEdge; i < claim.topEdge + claim.height; i++)
        for(int j = claim.leftEdge; j < claim.leftEdge + claim.width; j++)
          if (area[i][j] == null)
            area[i][j] = claim.id;
          else
            area[i][j] = "X";
    }

    int getOverlapSize() {
      int result = 0;
      for(int i = 0; i < area.length; i++)
        for(int j = 0; j < area.length; j++)
          if ("X".equals(area[i][j]))
            result++;

      return result;
    }

    String getNotOverlapedClaim() {
      for (String s : input) {
        Claim claim = new Claim(s);
        if (checkArea(claim))
          return claim.id;
      }
      return "";
    }

    private boolean checkArea(Claim claim) {
      for(int i = claim.topEdge; i < claim.topEdge + claim.height; i++)
        for(int j = claim.leftEdge; j < claim.leftEdge + claim.width; j++)
          if ("X".contains(area[i][j]))
            return false;

      return true;
    }

    private class Claim {
      String id;
      int leftEdge;
      int topEdge;
      int width;
      int height;

      // #1 @ 56,249: 24x16
      public Claim(String s) {
        id = s.split("@")[0].substring(1).trim();
        String rest = s.split("@")[1].trim();
        leftEdge = Integer.parseInt(rest.split(",")[0]);
        rest = rest.split(",")[1];
        topEdge = Integer.parseInt(rest.split(":")[0]);
        rest = rest.split(":")[1].trim();
        width = Integer.parseInt(rest.split("x")[0]);
        height = Integer.parseInt(rest.split("x")[1]);
      }
    }
  }
}
