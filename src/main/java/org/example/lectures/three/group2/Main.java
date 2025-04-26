package org.example.lectures.three.group2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private boolean[][] arrView;
    private Node[][] nodes;
    private int endX;
    private int endY;

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
    public Node toGraph(
        boolean[][] arrView,
        int startX,
        int startY,
        int endX,
        int endY
    ) {
       if (!(startX == 0 || startX == arrView.length - 1 || startY == 0 || startY == arrView[0].length - 1) || arrView[startX][startY]) {
           throw new IllegalArgumentException("Invalid start position");
       } else if (!(endX == 0 || endX == arrView.length - 1 || endY == 0 || endY == arrView[0].length - 1) || arrView[endX][endY]) {
           throw new IllegalArgumentException("Invalid end position");
       }

       this.arrView = arrView;
       this.endX = endX;
       this.endY = endY;
       this.nodes = new Node[arrView.length][arrView[0].length];

       return toGraph(startX, startY);
    }


    private Node toGraph(int currX, int currY) {
        Node mappedNode = nodes[currX][currY];
        if (mappedNode != null) {
            return mappedNode;
        }

        Node curr = new Node();
        nodes[currX][currY] = curr;


        if ((currX != 0) && !arrView[currX - 1][currY]) {
            curr.neighbours.add(toGraph(currX - 1, currY));
        }

        if ((currX != arrView.length - 1) && !arrView[currX + 1][currY]) {
            curr.neighbours.add(toGraph(currX + 1, currY));
        }

        if ((currY != 0) && !arrView[currX][currY - 1]) {
            curr.neighbours.add(toGraph(currX, currY - 1));
        }

        if ((currY != arrView[0].length - 1) && !arrView[currX][currY + 1]) {
            curr.neighbours.add(toGraph(currX, currY + 1));
        }

        if (currX == endX && currY == endY) {
            curr.isFinal = true;
        }

        return curr;
    }
    private final Map<Node, WeightedNode> map = new HashMap<>();

    public WeightedNode compress(Node root) {
        map.clear();
        return compress(root, null, 0, null);
    }

    private WeightedNode compress(Node node,
                                  Node prevNode,
                                  int accWeight,
                                  WeightedNode prevWeighted) {

        // if it already exists, just connect back to previous
        WeightedNode existing = map.get(node);
        if (existing != null) {
            if (prevWeighted != null)
                prevWeighted.outgoingEdges.add(
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

        // branching point, or final node,
        WeightedNode here = new WeightedNode();
        here.isFinal = node.isFinal;
        map.put(node, here);

        // connect to previous weighted+
        if (prevWeighted != null)
            prevWeighted.outgoingEdges.add(
                    new WeightedNode.Edge(here, accWeight));

        // start walking on each node from branching point
        for (Node nb : next)
            compress(nb, node, 1, here);

        return here;
    }




}
