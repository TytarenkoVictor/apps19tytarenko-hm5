package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntPredicate;

public class IntPredicateIterator implements IteratorExtends<Integer> {

    private IteratorExtends<Integer> iterator;
    private IteratorExtends<Integer> copyIterator;
    private IntPredicate predicate;

    public IntPredicateIterator(IteratorExtends<Integer> iterator, IntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        IteratorExtends<Integer> copyIterator = iterator.copy();
        while (copyIterator.hasNext()) {
            if (predicate.test(copyIterator.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        copyIterator = iterator.copy();
        int count;
        for (count = -1; copyIterator.hasNext(); count++) {
            if (predicate.test(copyIterator.next())) {
                break;
            }
        }
        for (int i = 0; i < count; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public IteratorExtends<Integer> copy() {
        return iterator.copy();
    }
}
