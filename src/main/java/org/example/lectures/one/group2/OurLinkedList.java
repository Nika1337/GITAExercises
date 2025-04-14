package org.example.lectures.one.group2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OurLinkedList implements List<Integer> {
    private Node<Integer> head = null;
    @Override
    public int size() {
        int size = 0;

        for (Node<Integer> curr = head; curr != null; curr = curr.next) {
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {

        for (Node<Integer> curr = head; curr != null; curr = curr.next) {
            if (o.equals(curr.value)) {
                return true;
            }
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
        if (isEmpty()) {
            head = new Node<>(integer);
            return true;
        }

        Node<Integer> curr = head;

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = new Node<>(integer);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }


        for (Node<Integer> curr = head; curr.next != null; curr = curr.next) {
            if (o.equals(curr.next.value)) {
                curr.next = curr.next.next;
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
    public Integer get(int index) {
        return getNodeAt(index).value;
    }

    @Override
    public Integer set(int index, Integer element) {

        Node<Integer> node = getNodeAt(index);

        Integer result = node.value;

        node.value = element;

        return result;
    }

    @Override
    public void add(int index, Integer element) {
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            head = new Node<>(element, head);
            return;
        }

        Node<Integer> node = getNodeAt(index - 1);

        node.next = new Node<>(element, node.next);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            Integer result = head.value;
            head = head.next;
            return result;
        }

        Node<Integer> node = getNodeAt(index - 1);

        Integer result = node.next.value;
        node.next = node.next.next;

        return result;
    }

    private Node<Integer> getNodeAt(int index) {
        Node<Integer> curr = head;

        for (int i = 0; i <= index; i++) {
            if (curr == null) {
                throw new IndexOutOfBoundsException();
            }
            curr = curr.next;
        }

        return curr;
    }
    @Override
    public int indexOf(Object o) {

        int i = 0;
        for (Node<Integer> curr = head; curr != null; curr = curr.next) {
            if (o.equals(curr.value)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int i = 0;

        for (Node<Integer> curr = head; curr != null; curr = curr.next) {
            if (o.equals(curr.value)) {
                index = i;
            }
            i++;
        }

        return index;
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
