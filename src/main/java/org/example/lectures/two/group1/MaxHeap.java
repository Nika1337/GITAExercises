package org.example.lectures.two.group1;

import java.util.ArrayList;

public class MaxHeap extends BinaryTree {
    private ArrayList<Integer> innerList = new ArrayList<>();

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int temp = innerList.get(i);
        innerList.set(i, innerList.get(j));
        innerList.set(j, temp);
    }

    private void heapifyUp(int i) {
        while (i > 0 && innerList.get(parent(i)) < innerList.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int biggest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < innerList.size() && innerList.get(left) > innerList.get(biggest)) {
            biggest = left;
        }

        if (right < innerList.size() && innerList.get(right) > innerList.get(biggest)) {
            biggest = right;
        }


        if (biggest != i) {
            swap(i, biggest);
            heapifyDown(biggest);
        }
    }

    @Override
    public void insert(int value) {
        innerList.add(value);
        heapifyUp(innerList.size() - 1);
    }

    public int extractMaximum() {
        if (innerList.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int maximum = innerList.get(0);
        int last = innerList.remove(innerList.size() - 1);

        if (!innerList.isEmpty()) {
            innerList.set(0, last);
            heapifyDown(0);
        }

        return maximum;
    }

    public int peek() {
        if (innerList.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return innerList.get(0);
    }
}






