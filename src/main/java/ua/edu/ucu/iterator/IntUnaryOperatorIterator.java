package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntUnaryOperator;


public class IntUnaryOperatorIterator implements IteratorExtends<Integer> {

    private IteratorExtends<Integer> iterator;
    private IntUnaryOperator operator;

    public IntUnaryOperatorIterator(IteratorExtends<Integer> iterator, IntUnaryOperator operator) {
        this.iterator = iterator;
        this.operator = operator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return operator.apply(iterator.next());
    }


    @Override
    public IteratorExtends<Integer> copy() {
        return iterator.copy();
    }
}
