package day23;

import java.util.ArrayList;
import java.util.List;

public class Input {
  public static final String INPUT =
       "set b 81\n"
      +"set c b\n"
      +"jnz a 2\n"
      +"jnz 1 5\n"
      +"mul b 100\n"
      +"sub b -100000\n"
      +"set c b\n"
      +"sub c -17000\n"
      +"set f 1\n"
      +"set d 2\n"
      +"set e 2\n"
      +"set g d\n"
      +"mul g e\n"
      +"sub g b\n"
      +"jnz g 2\n"
      +"set f 0\n"
      +"sub e -1\n"
      +"set g e\n"
      +"sub g b\n"
      +"jnz g -8\n"
      +"sub d -1\n"
      +"set g d\n"
      +"sub g b\n"
      +"jnz g -13\n"
      +"jnz f 2\n"
      +"sub h -1\n"
      +"set g b\n"
      +"sub g c\n"
      +"jnz g 2\n"
      +"jnz 1 3\n"
      +"sub b -17\n"
      +"jnz 1 -23";

  public static List<Instruction> parse (String input) {
    List<Instruction> result = new ArrayList<>();
    for (String line : input.split("\n")) {
      result.add(parseLine(line));
    }
    return result;
  }

  private static Instruction parseLine(String line) {
    String[] partOfInstruction = line.split(" ");
    InstructionType type = InstructionType.of(partOfInstruction[0]);
    ValueFactory valueFactory = new ValueFactory();
    InstructionValue firstValue = valueFactory.create(partOfInstruction[1]);
    InstructionValue secondValue = valueFactory.create(partOfInstruction[2]);
    return new Instruction(type, firstValue, secondValue);
  }
}
