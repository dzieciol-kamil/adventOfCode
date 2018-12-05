package day18.part1;

import day18.common.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Interpreter {

  private final HashMap<Character, Long> register;
  private final List<Instruction> instructions;
  private Long lastPlayerFrequency;
  private Long lastRecoverFrequency;
  private Long oneSendCounter;

  public Interpreter(List<String> input) {
    lastPlayerFrequency = 0l;
    register = new HashMap<>();
    instructions = input.stream().map(Instruction::parse).collect(Collectors.toList());
  }

  public Long getLastRecoverFrequency() {
    return lastRecoverFrequency;
  }

  public Long getProgramOneMessageSendCounter() {
    return oneSendCounter;
  }

  public void interpret() {
    int position = 0;
    while (position >= 0 && position < instructions.size()) {
      Instruction instruction = instructions.get(position);
      InstructionInterpreter interpreter = new InstructionInterpreter(register);
      interpreter.interpret(instruction, lastPlayerFrequency, position);
      lastPlayerFrequency = interpreter.getLastPlayerFrequency();
      lastRecoverFrequency = interpreter.getLastRecoverFrequency();
      position = interpreter.getPosition();
    }
  }
}
