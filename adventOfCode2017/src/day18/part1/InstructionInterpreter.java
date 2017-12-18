package day18.part1;

import day18.common.Instruction;

import java.util.HashMap;

public class InstructionInterpreter {

  private final HashMap<Character, Long> register;
  private Long lastPlayerFrequency;
  private int position;
  private Long lastRecoverFrequency;

  public InstructionInterpreter(HashMap<Character, Long> register) {
    this.register = register;
  }

  public Long getLastRecoverFrequency() {
    return lastRecoverFrequency;
  }

  public void interpret(Instruction instruction, Long lastPlayerFrequency, int position) {
    this.lastPlayerFrequency = lastPlayerFrequency;
    this.position = position;
    interpret(instruction);
  }

  public void interpret(Instruction instruction, int position) {
    interpret(instruction, 0l, position);
  }

  private void interpret(Instruction instruction) {
    switch (instruction.getType()) {
      case SOUND:
        sound(instruction);
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
        recover(instruction);
        break;
      case JUMP:
        jump(instruction);
        return;
    }
    position++;
  }

  private void jump(Instruction instruction) {
    if (instruction.isOn(register, value -> value > 0))
      position += instruction.getValue(register);
    else
      position++;
  }

  private void recover(Instruction instruction) {
    if (instruction.isOn(register, value -> value != 0)) {
      lastRecoverFrequency = lastPlayerFrequency;
      position = Integer.MIN_VALUE;
    }
  }

  private void modulo(Instruction instruction) {
    Long value = getFromRegister(instruction) % instruction.getValue(register);
    register.put(instruction.getRegister(), value);
  }

  private void multiply(Instruction instruction) {
    Long value = getFromRegister(instruction) * instruction.getValue(register);
    register.put(instruction.getRegister(), value);
  }

  private void add(Instruction instruction) {
    Long value = getFromRegister(instruction) + instruction.getValue(register);
    register.put(instruction.getRegister(), value);
  }

  private void sound(Instruction instruction) {
    lastPlayerFrequency = register.get(instruction.getRegister());
  }

  private void set(Instruction instruction) {
    register.put(instruction.getRegister(), instruction.getValue(register));
  }

  private Long getFromRegister(Instruction instruction) {
    return register.getOrDefault(instruction.getRegister(), 0l);
  }

  public Long getLastPlayerFrequency() {
    return lastPlayerFrequency;
  }

  public int getPosition() {
    return position;
  }
}
