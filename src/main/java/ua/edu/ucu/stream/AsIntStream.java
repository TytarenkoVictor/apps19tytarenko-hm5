package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterator.*;

import java.util.ArrayList;

import static ua.edu.ucu.util.Util.toObject;

public class AsIntStream implements IntStream {

    private IteratorExtends<Integer> iterator;

    private AsIntStream(int... values) {
        iterator = new IteratorImplementation<>(toObject(values));
    }

    private AsIntStream setIterator(IteratorExtends<Integer> iterator) {
        this.iterator = iterator;
        return this;
    }

    private boolean isEmpty() {
        return !iterator.hasNext();
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        int[] tmp = toArray();
        AsIntStream sumCopy = (AsIntStream) of(tmp);
        double sum = sumCopy.sum();
        AsIntStream countCopy = (AsIntStream) of(tmp);
        long count = countCopy.count();
        return sum / count;
    }

    @Override
    public Integer max() {
        return reduce(Integer.MIN_VALUE, Math::max);
    }

    @Override
    public Integer min() {
        return reduce(Integer.MAX_VALUE, Math::min);
    }

    @Override
    public long count() {
        return reduce(0, (count, x) -> count + 1);
    }

    @Override
    public Integer sum() {
        return reduce(0, Integer::sum);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream().setIterator(new IntPredicateIterator(iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream().setIterator(new IntUnaryOperatorIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream().setIterator(new IntToIntStreamFunctionIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int toReturn = identity;
        int tmp;
        while (iterator.hasNext()) {
            tmp = iterator.next();
            toReturn = op.apply(toReturn, tmp);
        }
        return toReturn;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> tmp = new ArrayList<>();
        while (iterator.hasNext()){
            tmp.add(iterator.next());
        }
        int[] toReturn = new int[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            toReturn[i] = tmp.get(i);
        }
        return toReturn;
    }
}
