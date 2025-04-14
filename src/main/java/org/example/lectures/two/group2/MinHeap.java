package org.example.lectures.two.group2;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> heap = new ArrayList<>();

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // array[5] = 3; array[10] = 13;
    // array[5] = 13; array[10] = 3;
    private void swap(int i, int j) {
        int tmp = heap.get(i);

        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int minimal = i;
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);

        if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minimal)) {
            minimal = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minimal)) {
            minimal = rightChild;
        }

        if (minimal != i) {
            swap(i, minimal);
            heapifyDown(minimal);
        }
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int extractMinimal() {
        if (heap.isEmpty()) {
            throw new IllegalStateException();
        }

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }
}
