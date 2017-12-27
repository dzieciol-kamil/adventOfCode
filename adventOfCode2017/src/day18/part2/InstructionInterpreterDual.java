package day18.part2;

import day18.common.Instruction;

import java.util.HashMap;
import java.util.Queue;

public class InstructionInterpreterDual {

  private final HashMap<Character, Long> register;
  private int position;
  private boolean lock;
  private long sendCounter;

  public InstructionInterpreterDual(HashMap<Character, Long> register) {
    this.register = register;
  }

  public boolean isLock() {
    return lock;
  }

  public long getSendCounter() {
    return sendCounter;
  }

  public void interpret(Instruction instruction,
                        int position,
                        Queue<Long> inQueue,
                        Queue<Long> outQueue,
                        long sendCounter) {
    this.position = position;
    this.sendCounter = sendCounter;
    switch (instruction.getType()) {
      case SOUND:
        send(instruction, outQueue);
        break;
      case SET:
        set(instruction);
        break;
      case ADD:
        add(instruction);
        break;
      case MULTIPLY:
        multiply(instruction);
        break;
      case MODULO:
        modulo(instruction);
        break;
      case RECOVER:
        recive(instruction, inQueue);
        break;
      case JUMP:
        jump(instruction);
        break;
    }
  }

  private void jump(Instruction instruction) {
    if (instruction.isOn(register, value -> value > 0))
      position += instruction.getValue(register);
    else
      position++;
  }

  private void recive(Instruction instruction, Queue<Long> inQueue) {
    lock = inQueue.isEmpty();

    if (inQueue.isEmpty())
      return;

    register.put(instruction.getRegister(), inQueue.poll());
    this.position++;
  }

  private void modulo(Instruction instruction) {
    Long value = getFromRegister(instruction) % instruction.getValue(register);
    register.put(instruction.getRegister(), value);
    this.position++;
  }

  private void multiply(Instruction instruction) {
    Long value = getFromRegister(instruction) * instruction.getValue(register);
    register.put(instruction.getRegister(), value);
    this.position++;
  }

  private void add(Instruction instruction) {
    Long value = getFromRegister(instruction) + instruction.getValue(register);
    register.put(instruction.getRegister(), value);
    this.position++;
  }

  private void send(Instruction instruction, Queue<Long> outQueue) {
    outQueue.add(instruction.getValue(register));
    sendCounter++;
    this.position++;
  }

  private void set(Instruction instruction) {
    register.put(instruction.getRegister(), instruction.getValue(register));
    this.position++;
  }

  private Long getFromRegister(Instruction instruction) {
    return register.getOrDefault(instruction.getRegister(), 0l);
  }

  public int getPosition() {
    return position;
  }
}
