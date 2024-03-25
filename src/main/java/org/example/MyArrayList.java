package org.example;

import java.util.Comparator;

/**
 * MyArrayList is realization of ArrayList class with the most used methods.
 * @param <E> the type of elements in this list.
 */
public class MyArrayList<E> {
    private final int DEFAULT_CAPACITY = 10;
    private final double DEFAULT_MULTIPLIER = 1.5;
    private static Object[] objects;
    private int capacity;
    private int pointer;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        objects = new Object[capacity];
        pointer = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param initialCapacity the initial capacity of the list.
     * @throws IllegalArgumentException if the specified initial capacity is negative.
     */
    public MyArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity<0) throw new IllegalArgumentException();
        capacity = initialCapacity;
        objects = new Object[capacity];
        pointer = 0;
    }

    /**
     * Appends element in the end of the list.
     * @param e element to append in the list.
     * @return true if list has changed.
     */
    public boolean add(E e) {
        if (pointer == capacity)
            resize(Math.max((int) (capacity * DEFAULT_MULTIPLIER), (capacity + 1)));
        objects[pointer] = e;
        pointer++;
        return true;
    }

    /**
     * Inserts specified element at specified position in the list.
     * @param index position to insert the element
     * @param e element to be inserted
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void add(int index, E e) throws IndexOutOfBoundsException {
        checkIndex(index);
        for (int i=index; i<pointer; i++) {
            e = set(i, e);
        }
        add(e);
    }

    /**
     * Returns the number of elements in the list
     * @return the number of elements in the list
     */
    public int size() {
        return pointer;
    }

    /**
     * Returns element from the specified position in the list
     * @param index index of position to return
     * @return element from the specified position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return (E) objects[index];
    }

    /**
     * Removes element from the specified position in the list.
     * @param index index of position to remove.
     * @return removed element.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public E remove (int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        E res = get(index);
        for (int i = index; i<pointer-1; i++) {
            set(i, get(i+1));
        }
        pointer--;
        int possibleCapacity = (int)(capacity/DEFAULT_MULTIPLIER);
        if (pointer<possibleCapacity) {
            resize(possibleCapacity);
        }
        return res;
    }

    /**
     * Removes first appearance of the specified element in the list (if element is present).
     * @param e element to be removed from this list.
     * @return true if list has changed.
     */
    public boolean remove(E e) {
        for (int i=0; i<pointer; i++) {
            if (e == null ? get(i)==null : e.equals(get(i))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        capacity = DEFAULT_CAPACITY;
        objects = new Object[capacity];
        pointer = 0;
    }

    /**
     * Replaces element in the specified position with the specified element.
     * @param index index of the element to be replaced.
     * @param e element to be stored at specified position.
     * @return previous element at the specified position.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public E set(int index, E e) throws IndexOutOfBoundsException {
        checkIndex(index);
        E old = get(index);
        objects[index] = e;
        return old;
    }

    /**
     * Sorts list according to specified comparator.
     * @param comparator comparator used to compare list elements.
     * @throws NullPointerException if specified comparator is null
     */
    public void sort(Comparator<? super E> comparator) throws NullPointerException {
        if (comparator == null)
            throw new NullPointerException();
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i=0; i<pointer-1; i++) {
                if (comparator.compare(get(i), get(i+1))>0) {
                    swap(i, i+1);
                    isSorted = false;
                }
            }
        }
    }

    /**
     * Sorts list using quicksort according to specified comparator.
     * @param comparator comparator used to compare list elements.
     * @throws NullPointerException if specified comparator is null
     */
    public void quicksort(Comparator<? super E> comparator) throws NullPointerException {
        if (comparator == null)
            throw new NullPointerException();
        quicksort(comparator, 0, pointer-1);
    }

    /**
     * Returns a string representation of the list.
     * @return a string representation of the list.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i=0; i<pointer; i++) {
            result.append(get(i));
            if (i+1!=pointer) result.append(", ");
        }
        return result.append("]").toString();
    }

    /**
     * Trims the capacity of the list to its current size.
     */
    public void trimToSize() {
        resize(pointer);
    }
    private void quicksort(Comparator<? super E> comparator, int first, int last) {
        if (first<last) {
            E value = get(last);
            int place = first;
            for (int i=first; i<last; i++) {
                if (comparator.compare(get(i),value)<=0) {
                    if (place!=i) {
                        swap(place, i);
                    }
                    place++;
                }
            }
            swap(place, last);
            quicksort(comparator, first, place - 1);
            quicksort(comparator, place + 1, last);
        }
    }
    private void swap(int a, int b) {
        set(b, set(a, get(b)));
    }
    private void checkIndex(int index) {
        if (index<0 || index>=pointer) throw new IndexOutOfBoundsException();
    }
    private void resize(int newSize) {
        Object[] newObjects = new Object[newSize];
        System.arraycopy(objects, 0, newObjects, 0, pointer);
        objects = newObjects;
        capacity = newSize;
    }
}
