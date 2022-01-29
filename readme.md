# Implementation of Range List

> author: Jiale Dai
>
> Email: jiale.dai@nyu.edu

## Improvement:

Besides the requirement in question. I did some features which is also commonly used in daliy works.

- Generic

  I implement the RangeList in generic format. It can take any comparable data type.

- Thread Safe

  My implementation is based on TreeMap, which is a non- tread- safe class. So for add and remvoe functions used in multi-thread environment. There will be race condition. I avoid race condition by adding locks.

## RangedCollection

### Interface

- void add(E[] range);
- void remove(E[] range);

## RangeList&lt;E extends Comparable&gt;

### extends TreeMap<E,E>

### implements RangedCollection

This implementation is based on TreeMap. Which is a sorted hashmap in Java. The sorting order is based on key. Given a range [lowerBound, upperBound], it is stored in TreeMap in the form of lowerBound as the key, and upperBound as the value.

### Main Methods:

1. void maintainRangeList()

​		A range list is not supposed to have overlap intervals. The upperBound of the previoud entry should be smaller than the lowerBound of the next entry. This method is used to maintain a range list to remove the overlaps.

2. void add(Comparable[] arr)

   - implementation: For the valid input range, we add it to the TreeMap first. Then call the maintainRangeList() method to make sure it is still a vaid rangeList.

   - Usage:

   ```java
   RangedCollection<Integer> rangeList = new RangeList<>();
   rangeList.add(new Integer[]{1,2});
   ```

3. void remvoe(Comparable[]  arr)

   - implementation (persudocode):

     ```
     void remove(input) {
     		find a range [a, b] containing input.lowerBound;
     		if (b > input.lowerBound and a <= input.LowerBound) {
     				if (a < input.lowerBound) {
     						repleace the range [a, b] with [a, input.lowerBound];
     				} else {
     						// a == input.lowerBound
     						if (b > input.upperBound) {
     								replace the range [a, b] with [input.upperBound, b];
     								return;
     						}
     				}
     				while (b < input.upperBound) {
     						a = nextRange.lowerBound;
     						b = nextRange.upperBound;
     						if (a <= input.lowerBound) {
     								remove range[a, b] from treemap;
     						}
     				}
     				put the range [input.upperBound, b] to treemap
     		}
     }
     ```

   - Usage:

   ```java
   RangedCollection<Integer> rangeList = new RangeList<>();
   rangeList.add(new Integer[]{1,2});
   ```

   ## Unit Test

   ### Add

   1. null - raise IllegalArgument Exception

   2. [2, 1] - raise IllegalArgument Exception

   3. [1, 2, 3] - raise IllegalArgument Exception

   4. [1, 5], [10, 20], [20, 20], [20, 21], [2, 4], [3, 8]

      result:

      ![Screen Shot 2022-01-29 at 2.41.41 PM](https://tva1.sinaimg.cn/large/008i3skNgy1gyv5ygb356j30u00v1tbt.jpg)

### Remove

1. null - raise IllegalArgument Exception

2. [2, 1] - raise IllegalArgument Exception

3. [1, 2, 3] - raise IllegalArgument Exception

4. add: [1, 5], [10, 20], [20, 20], [20, 21], [2, 4], [3, 8]

   remove: [10, 10], [10, 11], [15, 17], [3, 19]

   result:

   ![Screen Shot 2022-01-29 at 2.45.37 PM](https://tva1.sinaimg.cn/large/008i3skNgy1gyv61w079pj30ty0hi758.jpg)
