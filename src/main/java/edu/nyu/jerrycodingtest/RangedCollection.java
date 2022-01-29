package edu.nyu.jerrycodingtest;

public interface RangedCollection<E extends Comparable<E>> {
    void add(E[] range);

    void remove(E[] range);
}
