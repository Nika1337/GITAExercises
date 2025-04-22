package org.example.lectures.three.group1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        visited[currX][currY] = true;
    }


    public WeightedNode collapse(Node node) {


        WeightedNode result = new WeightedNode();

        collapse(node, null, result, 0);

        return result;
    }

    private void collapse(Node oldCurrNode, Node oldPrevNode, WeightedNode newCurrNode, int accumulatedWeight) {

        List<Node> currNeighbours = oldCurrNode.neighbours;

        if (currNeighbours.size() == 1 && oldPrevNode != null) {

            WeightedNode newNode = new WeightedNode();
            newNode.edges.add(new WeightedNode.Edge(newCurrNode, accumulatedWeight));
            newCurrNode.edges.add(new WeightedNode.Edge(newNode, accumulatedWeight));

        } else if (currNeighbours.size() == 2) {

            Node nextElement = null;

            for (Node neighbour : currNeighbours) {
                if (neighbour != oldPrevNode) {
                    nextElement = neighbour;
                    break;
                }
            }

            collapse(nextElement, oldCurrNode, newCurrNode, accumulatedWeight + 1);
        } else {

            for (Node neighbour : currNeighbours) {
                if (neighbour == oldPrevNode) {
                    continue;
                }

                WeightedNode newNode = new WeightedNode();
                newNode.edges.add(new WeightedNode.Edge(newCurrNode, accumulatedWeight));
                newCurrNode.edges.add(new WeightedNode.Edge(newNode, accumulatedWeight));

                collapse(neighbour, oldCurrNode, newNode, 0);
            }

        }


    }
}
