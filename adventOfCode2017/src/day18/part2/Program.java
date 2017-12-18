package day18.part2;

import day18.common.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class Program {
  
  private final HashMap<Character, Long> register;
  private final List<Instruction> instructions;
  private boolean lock;
  private int position;
  private long sendCounter;

  public Program(List<Instruction> instructions, long programValue) {
    this.instructions = instructions;
    register = new HashMap<>();
    register.put('p', programValue);
    position = 0;
    lock = false;
  }

  public void interpret(Queue<Long> inQueue,
                        Queue<Long> outQueue) {
    if (position < 0 || position >= instructions.size()) {
      lock = true;
      return;
    }
    Instruction instruction = instructions.get(position);
    InstructionInterpreterDual interpreter = new InstructionInterpreterDual(register);
    interpreter.interpret(instruction, position, inQueue, outQueue, sendCounter);
    lock = interpreter.isLock();
    sendCounter = interpreter.getSendCounter();
    position = interpreter.getPosition();
  }

  public long getSendCounter() {
    return sendCounter;
  }

  public boolean lock() {
    return lock;
  }
}
