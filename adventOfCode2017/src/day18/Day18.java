package day18;

import advent.AdventClass;
import day18.common.Input;
import day18.part1.Interpreter;
import day18.part2.InterpreterDual;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day18 implements AdventClass{

  private final List<String> INPUT = Arrays.asList(Input.INPUT.split("\n"));

  @Override
  public String printFirst() {
    Interpreter interpreter = new Interpreter(INPUT);
    interpreter.interpret();
    return "Last played frequency = " + interpreter.getLastRecoverFrequency();
  }

  @Override
  public String printSecond() {
    InterpreterDual interpreter = new InterpreterDual(INPUT);
    interpreter.interpretDual();
    return "Program send message = " + interpreter.getProgramOneMessageSendCounter();
  }

  @Test
  public void testInterpreter() {
    String testInputString = "set a 1\n"
                           + "add a 2\n"
                           + "mul a a\n"
                           + "mod a 5\n"
                           + "snd a\n"
                           + "set a 0\n"
                           + "rcv a\n"
                           + "jgz a -1\n"
                           + "set a 1\n"
                           + "jgz a -2";
    List<String> testInput = Arrays.asList(testInputString.split("\n"));
    Interpreter interpreter = new Interpreter(testInput);

    interpreter.interpret();

    assertEquals(Long.valueOf(4L), interpreter.getLastRecoverFrequency());
  }

  @Test
  public void dualInterpreterTest() {
    String testInputString = "snd a\n"
                           + "snd a\n"
                           + "snd p\n"
                           + "rcv a\n"
                           + "rcv b\n"
                           + "rcv c\n"
                           + "rcv d";
    List<String> testInput = Arrays.asList(testInputString.split("\n"));
    InterpreterDual interpreter = new InterpreterDual(testInput);

    interpreter.interpretDual();

    assertEquals(Long.valueOf(3L), interpreter.getProgramOneMessageSendCounter());
  }
}
