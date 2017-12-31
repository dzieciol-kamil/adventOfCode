package day24;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {
  public static final String INPUT =
      "24/14\n"
    + "30/24\n"
    + "29/44\n"
    + "47/37\n"
    + "6/14\n"
    + "20/37\n"
    + "14/45\n"
    + "5/5\n"
    + "26/44\n"
    + "2/31\n"
    + "19/40\n"
    + "47/11\n"
    + "0/45\n"
    + "36/31\n"
    + "3/32\n"
    + "30/35\n"
    + "32/41\n"
    + "39/30\n"
    + "46/50\n"
    + "33/33\n"
    + "0/39\n"
    + "44/30\n"
    + "49/4\n"
    + "41/50\n"
    + "50/36\n"
    + "5/31\n"
    + "49/41\n"
    + "20/24\n"
    + "38/23\n"
    + "4/30\n"
    + "40/44\n"
    + "44/5\n"
    + "0/43\n"
    + "38/20\n"
    + "20/16\n"
    + "34/38\n"
    + "5/37\n"
    + "40/24\n"
    + "22/17\n"
    + "17/3\n"
    + "9/11\n"
    + "41/35\n"
    + "42/7\n"
    + "22/48\n"
    + "47/45\n"
    + "6/28\n"
    + "23/40\n"
    + "15/15\n"
    + "29/12\n"
    + "45/11\n"
    + "21/31\n"
    + "27/8\n"
    + "18/44\n"
    + "2/17\n"
    + "46/17\n"
    + "29/29\n"
    + "45/50";

  public static List<Component> parse (String input) {
    return Stream.of(input.split("\n"))
                 .map(line -> line.split("/"))
                 .map(splitedLine -> new int[]{Integer.parseInt(splitedLine[0]),
                                               Integer.parseInt(splitedLine[1])})
                 .map(intValues -> new Component(intValues[0], intValues[1]))
                 .collect(Collectors.toList());
  }
}
