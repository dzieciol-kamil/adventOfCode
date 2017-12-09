package day9;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Parser {

  private String parsed;
  private int garbageCount;

  public void parse(String input) {
    boolean negatinon = false;
    boolean garbage = false;
    parsed = "";
    garbageCount = 0;
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);

      if (isNegation(c)) {
        negatinon = !negatinon;
        continue;
      }

      if (isGarbageOpen(negatinon, garbage, c)) {
        garbage = true;
        continue;
      }

      if (isGarbageClose(negatinon, c)) {
        garbage = false;
        continue;
      }

      if (shouldCountChar(garbage, negatinon, c))
        garbageCount++;

      if (!garbage & !negatinon)
        parsed += c;

      negatinon = false;
    }
  }

  private boolean isGarbageClose(boolean negatinon, char c) {
    return c == '>' & !negatinon;
  }

  private boolean isGarbageOpen(boolean negatinon, boolean garbage, char c) {
    return c == '<' & !negatinon & !garbage;
  }

  private boolean isNegation(char c) {
    return c == '!';
  }

  private boolean shouldCountChar(boolean garbage, boolean negation, char c) {
    if (negation)
      return false;
    if (c == '!')
      return false;

    return garbage;
  }

  public int getGarbageCount() {
    return garbageCount;
  }

  public int getScore() {
    int deep = 0;
    int result = 0;
    for (int i = 0; i < parsed.length(); i++) {
      if (parsed.charAt(i) == '{') {
        deep++;
        result += deep;
      }
      if (parsed.charAt(i) == '}') {
        deep--;
      }
    }
    return result;
  }

  @Test
  public void testParseGarbage() {
    parse("<>");
    assertEquals("", parsed);
    assertEquals(0, garbageCount);
    parse("<asdsdasfadF>");
    assertEquals("", parsed);
    assertEquals(11, garbageCount);
    parse("<<<<<<<>" );
    assertEquals("", parsed);
    assertEquals(6, garbageCount);
    parse("<{!}>");
    assertEquals("", parsed);
    assertEquals(1, garbageCount);
    parse("<{!}!<dfsdfsd>");
    assertEquals("", parsed);
    assertEquals(8, garbageCount);
  }

  @Test
  public void testParseWithNoGarbage() {
    parse("<>a");
    assertEquals("a", parsed);
    parse("a<asdsdasfadF>");
    assertEquals("a", parsed);
    parse("a<<<aa<<<<>" );
    assertEquals("a", parsed);
    parse("a<{!}>");
    assertEquals("a", parsed);
    parse("a<{!}!>dfsdfsd>a");
    assertEquals("aa", parsed);
  }

  @Test
  public void testCharCountInGarbage() {
    parse("<>");
    assertEquals(0, garbageCount );
    parse("<<<<>");
    assertEquals(3, garbageCount );
    parse("<{!>}>");
    assertEquals(2, garbageCount );
    parse("!!>");
    assertEquals(0, garbageCount );
    parse("<!!!>>");
    assertEquals(0, garbageCount );
    parse("<{o'i!a,<{i<a>");
    assertEquals(10, garbageCount );
  }
}
