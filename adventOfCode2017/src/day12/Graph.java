package day12;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class Graph {

  private Map<Integer, List<Integer>> input;
  private Set<Integer> connected;
  private int groups;

  public Graph(Map<Integer, List<Integer>> input) {
    this.input = input;
    this.connected = new HashSet<>();
    this.groups = 0;
  }

  public int calcConnectionsTo(int root) {
    Boolean connectionFound = true;
    connected.add(root);
    while (connectionFound) {
      connectionFound = false;
      connected.forEach(input::remove);
      for (Integer key : input.keySet()) {
        if (connected.contains(key) || input.get(key).stream().anyMatch(connected::contains)) {
          connected.add(key);
          connected.addAll(input.get(key));
          connectionFound = true;
        }
      }
    }

    return connected.size();
  }

  public int calcGroups(int root) {
    calcConnectionsTo(root);
    groups++;
    while(input.size() > 0) {
      calcConnectionsTo(input.keySet().stream().findFirst().get());
      groups++;
    }
    return groups;
  }
}
//455, 1055, 1061, 1604
