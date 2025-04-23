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

        if ((currY != arrView.length - 1) && !arrView[currX][currY + 1]) {
            curr.neighbours.add(toGraph(currX, currY + 1));
        }

        if (currX == endX && currY == endY) {
            curr.isFinal = true;
        }

        return curr;
    }


    private HashMap<Node, WeightedNode> map;

    public WeightedNode compress(Node node, Node prevNode, int accWeight) {
        WeightedNode mappedNode = map.get(node);
        if (mappedNode != null) {
            return mappedNode;
        }



        Set<Node> nonPrevNeighbours = node.neighbours.stream()
                .filter(n -> n != prevNode)
                .collect(Collectors.toSet());

        WeightedNode newNode = new WeightedNode();

        if (nonPrevNeighbours.size() == 0) {

            newNode = new WeightedNode();
            WeightedNode.Edge newEdge = new WeightedNode.Edge(map.get(prevNode), accWeight);
            newNode.outgoingEdges.add(newEdge);

        } else if (nonPrevNeighbours.size() == 1) {

            Node neighbour = nonPrevNeighbours.stream().findFirst().get();
            mappedNode = map.get(neighbour);

            if (mappedNode != null) {
                newNode = new WeightedNode();
                WeightedNode.Edge newEdge = new WeightedNode.Edge(map.get(prevNode), accWeight);
                newNode.outgoingEdges.add(newEdge);
            } else {
                return compress(neighbour, node, accWeight + 1);
            }

        } else {

        }



        return null;
    }


}
