package edu.nyu.jerrycodingtest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit test for add():
 * invalid input:
 * 1. null
 * 2. [2,1]
 * 3. [1,2,3]
 * valid input
 * 4. [1, 5], [10, 20], [20, 20], [20, 21], [2, 4], [3, 8]
 */
class RangeListTest {


    /**
     * null
     * invalid input: expected to raise an exception.
     */
    @org.junit.jupiter.api.Test
    void add01() {
        try {
            System.out.println("++++++++++++++++++++++ add test 01 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(null);
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [2, 1]
     * invalid input: expected to raise an exception.
     */
    @org.junit.jupiter.api.Test
    void add02() {
        try {
            System.out.println("++++++++++++++++++++++ add test 02 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(new Integer[]{2, 1});
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [1,2,3]
     * invalid input: expected to raise an exception.
     */
    @org.junit.jupiter.api.Test
    void add03() {
        try {
            System.out.println("++++++++++++++++++++++ add test 03 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(new Integer[]{1, 2, 3});
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [1, 1]
     * invalid input: expected to raise an exception.
     */
    @org.junit.jupiter.api.Test
    void add04() {
        try {
            System.out.println("++++++++++++++++++++++ add test 04 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(new Integer[]{1, 1});
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [1, 5], [10, 20], [20, 20], [20, 21], [2, 4], [3, 8]
     */
    @org.junit.jupiter.api.Test
    void add05() {
        try {
            System.out.println("++++++++++++++++++++++ add test 05 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(new Integer[]{1, 5});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{10, 20});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{20, 20});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{20, 21});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{2, 4});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{3, 8});
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add [1, 5], [10, 20], [20, 20], [20, 21], [2, 4], [3, 8]
     * remove [10, 10]
     */
    @org.junit.jupiter.api.Test
    void remove01() {
        try {
            System.out.println("++++++++++++++++++++++ remove test 01 *************************");
            RangedCollection<Integer> rangeList = new RangeList<>();
            rangeList.add(new Integer[]{1, 5});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{10, 20});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{20, 20});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{20, 21});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{2, 4});
            System.out.println(rangeList);
            rangeList.add(new Integer[]{3, 8});
            System.out.println(rangeList);
            // remove
            System.out.println("=================remove ====================");
            rangeList.remove(new Integer[]{10, 10});
            System.out.println(rangeList);
            rangeList.remove(new Integer[]{10, 11});
            System.out.println(rangeList);
            rangeList.remove(new Integer[]{15, 17});
            System.out.println(rangeList);
            rangeList.remove(new Integer[]{3, 19});
            System.out.println(rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}