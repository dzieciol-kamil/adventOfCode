package day23;

import java.util.Map;

public interface InstructionValue {

  Long getValue(Map<Character, Long> register);
}
