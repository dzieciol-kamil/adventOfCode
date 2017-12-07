package day7;

import advent.AdventClass;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Day7 implements AdventClass{


  @Override
  public String printFirst() {
    Map<String, Node> parsedTree = parseTree(Input.INPUT);
    return "Root element name is = " + findRoot(parsedTree);
  }

  @Override
  public String printSecond() {
    Map<String, Node> parsedTree = parseTree(Input.INPUT);
    Node rootNode = buildTree(parsedTree, findRoot(parsedTree));
    return "Balanced element value is = " + findBalancedWeight();
  }

  private Map<String, Node> parseTree(String input) {
    List<String> listOfNodes = Arrays.asList(input.split("\n"));
    Map<String, Node> result = new HashMap<>();
    for (String node : listOfNodes) {
      List<String> nodeValues = Arrays.asList(node.split("->"));
      List<String> nodeNameAndWeight = Arrays.asList(nodeValues.get(0).split(" "));
      String name = nodeNameAndWeight.get(0).trim();
      int weight = Integer.parseInt(nodeNameAndWeight.get(1)
                                                     .replace("(","" )
                                                     .replace(")","" )
                                                     .trim());
      List<String> childs = Collections.emptyList();
      if (nodeValues.size() > 1) {
        childs = Arrays.stream(nodeValues.get(1).split(","))
                       .map(String::trim)
                       .collect(Collectors.toList());
      }
      result.put(name, new Node(name, weight, childs));
    }
    return result;
  }
  private Node unbalancedNode = null;

  private Node buildTree(Map<String, Node> parsedTree, String root) {
    Node rootNode = parsedTree.get(root);
    rootNode.setChildNodes(rootNode.getChild()
                                   .stream()
                                   .map(child -> buildTree(parsedTree, child))
                                   .collect(Collectors.toList()));
    int weight = rootNode.getChildNodes()
                          .stream()
                          .findFirst()
                          .map(Node::getWeightWithChildren)
                          .orElse(0);
    int sum = 0;
    for (Node node : rootNode.getChildNodes()) {
      if (weight != node.getWeightWithChildren())
        if (unbalancedNode == null)
          unbalancedNode = rootNode;
      sum += node.getWeightWithChildren();
    }
    rootNode.setChildWeight(sum);
    return rootNode;
  }

  private int findBalancedWeight() {
    int invalidSumWeigh = 0;
    int validSumWeigh = Integer.MAX_VALUE;
    Node invalidNode = null;
    for (Node node : unbalancedNode.getChildNodes()) {
      if (node.getWeightWithChildren() > invalidSumWeigh) {
        invalidSumWeigh = node.getWeightWithChildren();
        invalidNode = node;
      }
      if (node.getWeightWithChildren() < validSumWeigh) {
        validSumWeigh = node.getWeightWithChildren();
      }
    }
    return invalidNode.getWeight() - (invalidSumWeigh - validSumWeigh);
  }


  private String findRoot(Map<String, Node> treeDefinition) {
    if (Objects.isNull(treeDefinition) || treeDefinition.isEmpty())
      return null;

    List<Node> treeWithoutLeafs = treeDefinition.values()
                                                .stream()
                                                .filter(node -> node.getChild().size() != 0)
                                                .collect(Collectors.toList());
    for (Node node : treeWithoutLeafs) {
      String name = node.getName();
      if (!hasParent(treeWithoutLeafs, name))
        return name;
    }
    return null;
  }

  private boolean hasParent(List<Node> nodesWithoutLeafs, String nodeName) {
    for (Node node : nodesWithoutLeafs) {
      if (node.getChild().stream().anyMatch(nodeName::equals))
        return true;
    }
    return false;
  }

  @Test
  public void testHasParent() {
    assertTrue(hasParent(Arrays.asList(new Node("a", 0, Arrays.asList("b", "c", "d"))), "b"));
    assertTrue(hasParent(Arrays.asList(new Node("a", 0, Arrays.asList("b", "c", "d")),
                                       new Node("b", 0, Arrays.asList("e", "f", "g"))), "b"));
    assertFalse(hasParent(Arrays.asList(new Node("a", 0, Arrays.asList("b", "c", "d")),
                                       new Node("b", 0, Arrays.asList("e", "f", "g"))), "h"));
  }

  private static final String TEST_INPUT =
      "pbga (66)\n"
    + "xhth (57)\n"
    + "ebii (61)\n"
    + "havc (66)\n"
    + "ktlj (57)\n"
    + "fwft (72) -> ktlj, cntj, xhth\n"
    + "qoyq (66)\n"
    + "padx (45) -> pbga, havc, qoyq\n"
    + "tknk (41) -> ugml, padx, fwft\n"
    + "jptl (61)\n"
    + "ugml (68) -> gyxo, ebii, jptl\n"
    + "gyxo (61)\n"
    + "cntj (57)";

  @Test
  public void testParse() {
    Map<String, Node> stringNodeMap = parseTree(TEST_INPUT);
    assertTrue(stringNodeMap.keySet().containsAll(Arrays.asList("pbga","xhth","ebii","havc","ktlj",
                                                                "fwft","qoyq","padx","tknk","jptl",
                                                                "ugml","gyxo","cntj")));
  }

  @Test
  public void testFindingRoot() {
    assertEquals(null, findRoot(null));
    assertEquals("tknk", findRoot(parseTree(TEST_INPUT)));
    assertEquals("hmvwl", findRoot(parseTree(Input.INPUT)));
  }

  @Test
  public void testBalancedWithTest() {
    buildTree(parseTree(TEST_INPUT), "tknk");
    assertEquals(251, findBalancedWeight());
  }

  @Test
  public void testBalancedWithReal() {
    buildTree(parseTree(Input.INPUT), "hmvwl");
    assertEquals(1853, findBalancedWeight());
  }
}
