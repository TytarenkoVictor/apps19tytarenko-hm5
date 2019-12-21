package ua.edu.ucu.iterator;

import java.util.Arrays;

public class IteratorImplementation<T> implements IteratorExtends<T> {

    private T[] array;
    private int currentIndex;

    public IteratorImplementation(T[] array) {
        this.array = array;
        this.currentIndex = -1;
    }

    public IteratorImplementation(T[] array, int currentIndex) {
        this.array = Arrays.copyOf(array, array.length);
        this.currentIndex = currentIndex;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length -1 ;
    }

    @Override
    public T next() {
        return array[++currentIndex];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public IteratorImplementation<T> copy() {
        return new IteratorImplementation<>(array, currentIndex);
    }

}
