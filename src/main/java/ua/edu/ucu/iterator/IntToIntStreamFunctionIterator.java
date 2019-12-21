package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntToIntStreamFunction;

import static ua.edu.ucu.util.Util.toObject;

public class IntToIntStreamFunctionIterator implements IteratorExtends<Integer> {

    private IteratorExtends<Integer> iterator;
    private IteratorExtends<Integer> copyIterator;
    private IntToIntStreamFunction function;

    public IntToIntStreamFunctionIterator(IteratorExtends<Integer> iterator, IntToIntStreamFunction function) {
        this.iterator = iterator;
        this.copyIterator = new IteratorImplementation<>(new Integer[0]);
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        if (!copyIterator.hasNext() && iterator.hasNext()) {
            int next = iterator.next();
            copyIterator = new IteratorImplementation<>(toObject(function.applyAsIntStream(next).toArray()));
        }
        return copyIterator.hasNext();
    }

    @Override
    public Integer next() {
        return copyIterator.next();
    }

    public IteratorExtends<Integer> copy() {
        return iterator.copy();
    }
}
