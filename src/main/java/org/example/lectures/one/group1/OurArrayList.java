package org.example.lectures.one.group1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OurArrayList implements List<Integer> {
    Integer[] innerArray = new Integer[1];
    int size = 0;
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (int i = 0; i < size(); i++) {
            if (o.equals(innerArray[i])) {
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
        if (size() >= innerArray.length / 2) {
            resizeArray(innerArray.length * 2);
        }

        innerArray[size()] = integer;

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o) || o == null) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (o.equals(innerArray[i])) {

                for (int j = i; innerArray[j] != null; j++) {
                    innerArray[j] = innerArray[j+1];
                }
                size--;

                if (size() < innerArray.length / 4) {
                    resizeArray(innerArray.length / 2);
                }

                return true;
            }
        }


        return false;
    }
    private void resizeArray(int newSize) {
        Integer[] newArr = new Integer[newSize];

        for (int i = 0; i < size(); i++) {
            newArr[i] = innerArray[i];
        }

        innerArray = newArr;
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
        innerArray = new Integer[1];
        size = 0;
    }

    @Override
    public Integer get(int index) {
        return innerArray[index];
    }

    @Override
    public Integer set(int index, Integer element) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Integer result = innerArray[index];
        innerArray[index] = element;


        return result;
    }

    @Override
    public void add(int index, Integer element) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (size() >= innerArray.length / 2) {
            resizeArray(innerArray.length * 2);
        }

        for (int i = size(); i > index; i--) {
            innerArray[i] = innerArray[i-1];
        }

        innerArray[index] = element;

        size++;
    }

    @Override
    public Integer remove(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int result = innerArray[index];
        for (int j = index; innerArray[j] != null; j++) {
            innerArray[j] = innerArray[j+1];
        }
        size--;

        if (size() < innerArray.length / 4) {
            resizeArray(innerArray.length / 2);
        }

        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (o.equals(innerArray[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i > 0; i--) {
            if (o.equals(innerArray[i])) {
                return i;
            }
        }

        return -1;
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
