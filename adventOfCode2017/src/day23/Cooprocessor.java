package day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cooprocessor {

  private final List<Instruction> instructions;
  private final Map<Character, Long> register;
  private int actualPosition;
  private int multiplyCount;

  public Cooprocessor(List<Instruction> instructions, Object aRegisterValue) {
    this.instructions = instructions;
    this.register = new HashMap() {{
      put('a', aRegisterValue);
      put('b', 0l);
      put('c', 0l);
      put('d', 0l);
      put('e', 0l);
      put('f', 0l);
      put('g', 0l);
      put('h', 0l);
    }};
    actualPosition = 0;
    multiplyCount = 0;
  }

  public int getMultiplyCount() {
    return multiplyCount;
  }

  public Long getRegisterH() {
    return register.get('h');
  }

  public void run() {
    while (actualPosition >= 0 && actualPosition < instructions.size()) {
      if (actualPosition == 8) {
        register.put('f', isPrime(register.get('b')) ? 1L : 0L);
        actualPosition = 24;
      }
      runOneInstruction();
    }
  }

  private boolean isPrime(long n) {
    if (n%2==0) return false;
    for(int i=3;i*i<=n;i+=2) {
      if(n%i==0)
        return false;
    }
    return true;
  }

  public void runInDebug() {
    while (actualPosition >= 0 && actualPosition < instructions.size()) {
      runOneInstruction();
    }
  }

  private void runOneInstruction() {
    Instruction instruction = instructions.get(actualPosition);
    multiplyCount = instruction.multiplyCounterUpdate(multiplyCount);
    actualPosition = instruction.invoke(register, actualPosition);
  }
}
