package day7;

import java.util.List;
import java.util.Objects;

public class Node {
  private final String name;
  private final int weight;
  private final List<String> child;
  private List<Node> childNodes;
  private int childWeight;

  public Node(String name, int weight, List<String> child) {
    this.name = name;
    this.weight = weight;
    this.child = child;
  }

  public List<Node> getChildNodes() {
    return childNodes;
  }

  public void setChildNodes(List<Node> childNodes) {
    this.childNodes = childNodes;
  }

  public int getChildWeight() {
    return childWeight;
  }

  public void setChildWeight(int childWeight) {
    this.childWeight = childWeight;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  public int getWeightWithChildren() {
    return weight + childWeight;
  }

  public List<String> getChild() {
    return child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Node node = (Node) o;
    return weight == node.weight &&
        Objects.equals(name, node.name) &&
        child.equals(node.child);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, weight, child);
  }
}
