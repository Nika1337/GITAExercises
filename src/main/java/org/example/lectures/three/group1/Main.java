package org.example.lectures.three.group1;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 [
   [0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0]
   [0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0],
   [0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0],
   [0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1],
   [0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0],
   [1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0],
   [0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0],
   [0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1],
   [0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0],
 */
public class Main {

    private boolean[][] arrView;
    private int endX;
    private int endY;
    private boolean[][] visited;

    public Node toGraph(boolean[][] arrView, int startX, int startY, int endX, int endY) {
        if (!(startX == 0 || startX == arrView.length - 1 || startY == 0 || startY == arrView[0].length - 1) || arrView[startX][startY]) {
            throw new IllegalArgumentException("Incorrect Start node");
        } else if (!(endX == 0 || endX == arrView.length - 1 || endY == 0 || endY == arrView[0].length) || arrView[endX][endY]) {
            throw new IllegalArgumentException("Incorrect Start node");
        }

        this.arrView = arrView;
        this.endX = endX;
        this.endY = endY;
        this.visited = new boolean[arrView.length][arrView[0].length];

        Node result = new Node();

        toGraph(startX, startY, new Node());

        return result;
    }

    /**
     * recursive method which populates curr with neighbours according to arrView
     * @param currX X position
     * @param currY Y position
     * @param curr current node
     */
    private void toGraph(int currX, int currY, Node curr) {
        if (visited[currX][currY]) {
            return;
        }

        // check that we can move to neighbour and the neighbour is not visited and is not a wall
        if (currX != 0 && !visited[currX - 1][currY] && !arrView[currX - 1][currY]) {
            // create new node and connect current to it using neighbours
            Node newNode = new Node();
            curr.neighbours.add(newNode);
            newNode.neighbours.add(curr);
            toGraph(currX - 1, currY, newNode);
        }

        if (currX != arrView.length - 1 && !visited[currX + 1][currY] && !arrView[currX + 1][currY]) {
            Node newNode = new Node();
            curr.neighbours.add(newNode);
            newNode.neighbours.add(curr);
            toGraph(currX + 1, currY, newNode);
        }

        if (currY != 0 && !visited[currX][currY - 1] && !arrView[currX][currY - 1]) {
            Node newNode = new Node();
            curr.neighbours.add(newNode);
            newNode.neighbours.add(curr);
            toGraph(currX, currY - 1, newNode);
        }

        if (currY != arrView[0].length - 1 && !visited[currX][currY + 1] && !arrView[currX][currY + 1]) {
            Node newNode = new Node();
            curr.neighbours.add(newNode);
            newNode.neighbours.add(curr);
            toGraph(currX, currY + 1, newNode);
        }

        if (currX == endX && currY == endY) {
            curr.isFinal = true;
        }

        visited[currX][currY] = true;
    }


    private final Map<Node, WeightedNode> map = new HashMap<>();

    public WeightedNode compress(Node root) {
        map.clear();
        return compress(root, null, 0, null);
    }

    private WeightedNode compress(
        Node node,
        Node prevNode,
        int accWeight,
        WeightedNode prevWeighted
    ) {

        // if it already exists, just connect back to previous
        WeightedNode existing = map.get(node);
        if (existing != null) {
            if (prevWeighted != null)
                prevWeighted.edges.add(
                        new WeightedNode.Edge(existing, accWeight));
            return existing;
        }

        // include only forward neighbours, meaning tha prevnode is ignored
        Set<Node> next =
                node.neighbours.stream()
                        .filter(n -> n != prevNode)
                        .collect(Collectors.toSet());

        boolean isVertex = node.isFinal
                || next.size() != 1;

        // we only have one way, corridor
        if (!isVertex) {
            Node only = next.iterator().next();
            return compress(only, node, accWeight + 1, prevWeighted);
        }

        // branching point, or final node
        WeightedNode here = new WeightedNode();
        here.isFinal = node.isFinal;
        map.put(node, here);

        // connect to previous weighted+
        if (prevWeighted != null)
            prevWeighted.edges.add(
                    new WeightedNode.Edge(here, accWeight));

        // start walking on each node from branching point
        for (Node nb : next)
            compress(nb, node, 1, here);

        return here;
    }
}
