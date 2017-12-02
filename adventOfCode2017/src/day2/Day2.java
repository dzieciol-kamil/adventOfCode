package day2;

import advent.AdventClass;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Day2 implements AdventClass {

  private static final String INPUT =
      "1640\t590\t93\t958\t73\t1263\t1405\t1363\t737\t712\t1501\t390\t68\t1554\t959\t79\n"
    + "4209\t128\t131\t2379\t2568\t2784\t2133\t145\t3618\t1274\t3875\t158\t1506\t3455\t1621\t3799\n"
    + "206\t1951\t2502\t2697\t2997\t74\t76\t78\t1534\t81\t2775\t2059\t3026\t77\t2600\t3067\n"
    + "373\t1661\t94\t102\t2219\t1967\t1856\t417\t1594\t75\t100\t2251\t2200\t1825\t1291\t1021\n"
    + "57\t72\t51\t1101\t1303\t60\t1227\t421\t970\t1058\t138\t333\t1320\t1302\t402\t1210\n"
    + "4833\t5427\t179\t3934\t4533\t5124\t4832\t2088\t94\t200\t199\t1114\t4151\t1795\t208\t3036\n"
    + "759\t876\t110\t79\t1656\t1691\t185\t544\t616\t312\t757\t1712\t92\t97\t1513\t1683\n"
    + "1250\t1186\t284\t107\t1190\t1233\t573\t1181\t1041\t655\t132\t547\t395\t146\t119\t515\n"
    + "505\t1726\t79\t180\t86\t1941\t1597\t1785\t1608\t1692\t968\t1177\t94\t184\t91\t31\n"
    + "1366\t2053\t1820\t1570\t70\t506\t53\t415\t717\t1263\t82\t366\t74\t1255\t2020\t1985\n"
    + "2365\t5585\t2285\t4424\t5560\t3188\t3764\t187\t88\t223\t1544\t5023\t4013\t5236\t214\t196\n"
    + "1487\t1305\t1359\t1615\t6579\t2623\t4591\t150\t5030\t188\t146\t4458\t5724\t5828\t1960\t221\n"
    + "3114\t688\t3110\t334\t1921\t153\t4083\t131\t2234\t3556\t3573\t3764\t127\t919\t3293\t104\n"
    + "1008\t78\t1196\t607\t135\t1409\t296\t475\t915\t157\t1419\t1304\t153\t423\t163\t704\n"
    + "235\t4935\t4249\t3316\t1202\t221\t1835\t380\t249\t1108\t1922\t5607\t4255\t238\t211\t3973\n"
    + "1738\t207\t179\t137\t226\t907\t1468\t1341\t1582\t1430\t851\t213\t393\t1727\t1389\t632\n";

  private final List<List<Integer>> lists;

  public Day2() {
    lists = readInput(INPUT);
  }

  private List<List<Integer>> readInput(String input) {
    List<String> lines = Arrays.stream(input.split("\n")).collect(Collectors.toList());
    return lines.stream().map(this::getLine).collect(Collectors.toList());
  }

  private List<Integer> getLine(String input) {
    return Arrays.stream(input.split("\t"))
                 .mapToInt(Integer::parseInt)
                 .boxed()
                 .collect(Collectors.toList());
  }

  @Override
  public String printFirst() {
    return "Checksum with sum = " + calcChecksum(lists, this::calcLinesValuesWithMinMax);
  }

  @Override
  public String printSecond() {
    return "Checksum with div = " + calcChecksum(lists, this::calcLinesValuesWithDivide);
  }

  private int calcChecksum(List<List<Integer>> spreadsheet, Function<List<Integer>, Integer> method) {
    return spreadsheet.stream()
                      .map(method::apply)
                      .mapToInt(Integer::intValue)
                      .sum();
  }

  private Integer calcLinesValuesWithMinMax(List<Integer> integers) {
    int min = integers.stream().mapToInt(Integer::intValue).min().getAsInt();
    int max = integers.stream().mapToInt(Integer::intValue).max().getAsInt();
    return max - min;
  }

  private Integer calcLinesValuesWithDivide(List<Integer> integers) {
    for (int i = 0; i < integers.size(); i++) {
      for (int j = i+1; j< integers.size() ; j++) {
        Integer first = integers.get(i);
        Integer second = integers.get(j);
        if (first % second == 0)
          return first / second;
        if (second % first == 0)
          return second / first;
      }
    }
    return 0;
  }

  @Test
  public void testCalc() {
    assertEquals(0, calcChecksum(Arrays.asList(Arrays.asList(1)),
                                 this::calcLinesValuesWithMinMax));
    assertEquals(1, calcChecksum(Arrays.asList(Arrays.asList(1,2)),
                                 this::calcLinesValuesWithMinMax));
    assertEquals(2, calcChecksum(Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3)),
                                 this::calcLinesValuesWithMinMax));
  }

  @Test
  public void testReadInput() {
    assertEquals(Arrays.asList(Arrays.asList(1)), readInput("1"));
    assertEquals(Arrays.asList(Arrays.asList(1,2)), readInput("1\t2"));
    assertEquals(Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3)),
                 readInput("1\t2\n2\t3"));
  }

  @Test
  public void testLineChecksum(){
    assertEquals(0, (int)calcLinesValuesWithMinMax(Collections.singletonList(1)));
    assertEquals(2, (int)calcLinesValuesWithMinMax(Arrays.asList(2, 4)));
    assertEquals(10, (int)calcLinesValuesWithMinMax(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));
  }

  @Test
  public void testLineChecksumWithDiv() {
    assertEquals(2, (int)calcLinesValuesWithDivide(Arrays.asList(2, 4)));
    assertEquals(3, (int)calcLinesValuesWithDivide(Arrays.asList(3, 4, 5, 7, 9, 11)));
  }
}
