package edu.nyu.jerrycodingtest;

import java.util.*;

/**
 * Implement class of RangeList. It uses generic, any class extends Comparable can be passed and form a Range List.
 *
 * @author jiale
 * @date Jan 29, 2022
 */
public class RangeList<E extends Comparable> extends TreeMap<E, E> implements RangedCollection {

    public RangeList() {
    }

    public int size() {
        return super.size();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Check concern case of the input. The range is valid only if it contains two element, and the lowerBound is less than or equal to the upperBound.
     *
     * @param arr
     * @return if the input value is valid
     */
    private boolean isValidRange(Comparable[] arr) {
        if (arr == null || arr.length != 2 || arr[0].compareTo(arr[1]) > 0) {
            return false;
        }
        return true;
    }

    /**
     * method to add a range into rangeList, we just add the input range into list, and then do a post-processing to maintain the properties of rangeList.
     *
     * @param arr
     */
    @Override
    public void add(Comparable[] arr) {
        if (!isValidRange(arr)) {
            throw new IllegalArgumentException("Input format : [lowerBound, upperBound]");
        }
        if (arr[0].compareTo(arr[1]) != 0) {
            synchronized (this) {
                super.put((E) arr[0], (E) arr[1]);
            }
            maintainRangeList();
        }
    }

    /**
     * Remove the input range from rangeList.
     * First find the first lowerBound which is less than or equal to input lowerBound.
     * Then find the first upperBound which is larger than or equal to input upperBound.
     * In the meantime, remove all the ranges on the path
     *
     * @param arr
     */
    @Override
    public void remove(Comparable[] arr) {
        if (!isValidRange(arr)) {
            throw new IllegalArgumentException("Input format : [lowerBound, upperBound]");
        }
        if (arr[0].compareTo(arr[1]) != 0) {
            E inputKey = (E) arr[0];
            E inputValue = (E) arr[1];
            RangeList<E> copy = (RangeList<E>) this.clone();
            if (inputValue.compareTo(super.get(super.firstKey())) < 0 || inputKey.compareTo(super.get(super.lastKey())) > 0) {
                return;
            }
            for (Map.Entry<E, E> each : copy.entrySet()) {
                E curKey = each.getKey();
                E curValue = each.getValue();

                synchronized (this) {
                    // find the start point, which should be the smaller one from curKey and inputKey
                    if (curValue.compareTo(inputKey) > 0 && curKey.compareTo(inputKey) <= 0) {
                        if (curKey.compareTo(inputKey) < 0) {
                            // remove range from curKey to input key
                            this.remove(curKey);
                            this.put(curKey, inputKey);
                        } else {
                            if (curValue.compareTo(inputValue) > 0) {
                                this.remove(curKey);
                                this.put(inputValue, curValue);
                                return;
                            }
                        }
                        while (curValue != null && curValue.compareTo(inputValue) < 0) {
                            curKey = copy.higherKey(curKey);
                            curValue = copy.get(curKey);
                            if (curKey.compareTo(inputValue) <= 0) {
                                this.remove(curKey);
                            }
                        }
                        // curValue > inputValue, update the last element
                        this.put(inputValue, curValue);
                    }
                }
            }
        }
    }

    /**
     * Helper method to maintain properties of a rangeList.
     * one pass scan on the list, check overlaps and correct it.
     */
    private void maintainRangeList() {
        E lastKey = super.firstKey();
        E lastValue = super.get(lastKey);

        RangeList<E> copy = (RangeList<E>) this.clone();


        for (Map.Entry<E, E> each : copy.entrySet()) {
            E curKey = each.getKey();
            E curValue = each.getValue();

            if (curKey.compareTo(lastValue) <= 0) {
                this.remove(curKey);
                this.put(lastKey, lastValue.compareTo(curValue) > 0 ? lastValue : curValue);
            }
            lastKey = curKey;
            lastValue = curValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.size() == 0) {
            sb.append("range list is empty");
        } else {
            for (Map.Entry<E, E> each : super.entrySet()) {
                sb.append("[" + each.getKey() + ", " + each.getValue() + ") ");
            }
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
