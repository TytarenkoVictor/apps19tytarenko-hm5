package ua.edu.ucu.iterator;

import java.util.Iterator;

public interface IteratorExtends<T> extends Iterator<T> {

    IteratorExtends<T> copy();

}
