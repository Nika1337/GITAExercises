package org.example.lectures.one.group1;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OurLinkedList implements List<Integer> {
    private Node<Integer> head = null;
    @Override
    public int size() {
        int size = 0;
        Node<Integer> curr = head;

        while (curr != null) {
            size++;
            curr = curr.getNext();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<Integer> curr = head;

        while (curr != null) {
            if (curr.equals(o)) {
                return true;
            }
            curr = curr.getNext();
        }

        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        if (head == null) {
            head = new Node<>(integer);
            return true;
        }
        Node<Integer> curr = head;

        while (curr.hasNext()) {
            curr = curr.getNext();
        }

        curr.setNext(new Node<>(integer));

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }



        for (Node<Integer> curr = head; curr.hasNext(); curr = curr.getNext()) {

            if (curr.getNext().getValue().equals(o)) {
                curr.setNext(curr.getNext().getNext());
                return true;
            }

        }





        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Integer get(int index) {
        Node<Integer> curr = head;

        for (int i = 0; i < index; i++) {
            if (!curr.hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            curr = curr.getNext();
        }


        return curr.getValue();
    }

    @Override
    public Integer set(int index, Integer element) {
        Node<Integer> curr = head;

        for (int i = 0; i < index; i++) {
            if (!curr.hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            curr = curr.getNext();
        }

        int result = curr.getValue();
        curr.setValue(element);

        return result;
    }

    @Override
    public void add(int index, Integer element) {

    }

    @Override
    public Integer remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return null;
    }
}
