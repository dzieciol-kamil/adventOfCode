package day18.part2;

import day18.common.Instruction;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class InterpreterDual {

  private final Program firstProgram;
  private final Program secondProgram;
  private final Queue<Long> firstToSecondQueue;
  private final Queue<Long> secondToFirstQueue;

  public InterpreterDual(List<String> input) {
    List<Instruction> instructions = input.stream()
                                          .map(Instruction::parse)
                                          .collect(Collectors.toList());
    firstProgram = new Program(instructions, 0L);
    secondProgram = new Program(instructions, 1L);
    firstToSecondQueue = new ArrayDeque<>();
    secondToFirstQueue = new ArrayDeque<>();
  }

  public Long getProgramOneMessageSendCounter() {
    return secondProgram.getSendCounter();
  }

  public void interpretDual() {
    while (!(firstProgram.lock() && secondProgram.lock())) {
      firstProgram.interpret(secondToFirstQueue, firstToSecondQueue);
      secondProgram.interpret(firstToSecondQueue, secondToFirstQueue);
    }
  }
}
