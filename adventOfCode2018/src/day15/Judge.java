package day15;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Judge {

  private int count;

  public Judge(int gen_a, int gen_b, int a_div, int b_div, int pairs_compare) {
    this.count = 0;
    long a = gen_a;
    long b = gen_b;
    for (int i = 0; i < pairs_compare; i++) {
      a = getNext(a, 16807, a_div);
      b = getNext(b, 48271, b_div);
      if (compare16Bits(a,b))
        count++;
    }
  }

  private long getNext(long a, int factor, int divider) {
    long result = a;
    while (true) {
      result = genNextValue(result, factor);
      if (result % divider == 0)
        return result;
    }
  }

  private boolean compare16Bits(long a, long b) {
    String a_bit = get16Bits(Long.toBinaryString(a));
    String b_bit = get16Bits(Long.toBinaryString(b));
    return a_bit.equals(b_bit);
  }

  private String get16Bits(String bits) {
    bits = IntStream.range(0, 16 - bits.length())
                    .mapToObj(i -> "0")
                    .collect(Collectors.joining("")) + bits;
    return bits.substring(bits.length() - 16, bits.length());
  }

  public int getFinalCount() {
    return count;
  }

  private long genNextValue(long prev, int factor) {
    return prev * factor % 2147483647;
  }
}
