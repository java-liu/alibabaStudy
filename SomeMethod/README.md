# [java8Stream](/Users/liuys/IdeaProjects/alibabaStudy/SomeMethod/src/main/java/com/ljava/somemethod/java8Stream)
## Stream概述
Java 8 中的 Stream API 是一种可以提供用于操作集合和数组的新方式的 API。
>Stream 将要处理的元素集合看作一种流,在流的过程中,借助Stream API 可以对流中的元素进行各种操作,比如过滤,排序,聚合等操作,并且可以将流转换为其他类型的数据结构,比如集合,数组等.

`Stream`可以由数组或集合创建,对流的操作分为两种:
1. 中间操作,每次返回一个新的流,可以有多个
2. 终端操作,每个流只能进行一次终端操作,终端操作结束后流无法再次使用.终端操作会产生一个新的集合或者值.
另外,`Stream`有几个特性:
1. stream不存储数据,而是按照特定的规则对数据进行计算,一般会输出结果
2. stream 不会改变数据源,通常情况下会产生一个新的集合或者一个值.
3. stream 具有延迟执行特性,只有调用终端操作时,中间操作才会执行.

## Stream的创建
`Stream`可以通过集合数组创建.
1. 通过 `java.util.Collection.stream()`方法用集合创建流
```java
List<String> list = Arrays.asList("a","b","c");
//创建一个顺序流
Stream<String> stream = list.stream();
//创建一个并行流
Stream<String> parallelStream = list.parallelStream();
```
2. 通过`java.util.Arrays.stream(T[] array)`方法用数组创建流
```java
int[] array = {1,2,3,4,5};
IntStream stream = Arrays.stream(array);
```
3. 使用`stream`的静态方法: `of(),range(),rangeClosed(),iterate(),generate()`创建流
```java
Stream<Integer> stream = Stream.of(1,2,3,4,5);

Stream<Integer> stream1 = Stream.iterate(0,(x) -> x + 1).limit(4);
stream1.forEach(System.out::println);

Stream<Double> stream2 = Stream.generate(Math::random).limit(4);
stream2.forEach(System.out::println);
```
`stream`和`parallelStream`的区别:`stream`是顺序流,由主线程按顺序对流执行操作,`parallelStream`是并行流,由多个线程同时对流执行操作,
但前提是流中的数据处理没有顺序要求.
如果流中的数据量过大,建议使用`parallelStream`来提高流的处理效率.
除了直接创建并行流,还可以通过`parallel()`方法将顺序流转换为并行流.
```java
Optional<Integer> findFirst = list.stream().parallel().filter((x) -> x.equals("a")).findFirst();
```
### 3.1遍历/匹配(foreach/find/match)
[StreamDemo1](src/main/java/com/ljava/somemethod/java8Stream/StreamDemo1.java)
### 3.2聚合(max/min/count)
[聚合](src/main/java/com/ljava/somemethod/java8Stream/MaxMinCountStreamDemo.java)
### 3.3映射(map/flatMap)
映射,可以将一个流的元素按照一定的映射规则映射到另一个流中.分为`map()`和`flatMap()`.
+ `map()`:接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素.
+ `flatMap()`:接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流.
### 3.4 归约(reduce)
归约,也称缩减,是把一个流缩减成一个值,能实现流中的元素进行某种计算,得到一个结果.能实现对集合求和,求乘积和求最值操作.
[归约](src/main/java/com/ljava/somemethod/java8Stream/ReduceStreamDemo.java)
### 3.5 收集(collect)
`collect`，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
> collect主要依赖java.util.stream.Collectors类内置的静态方法。
#### 3.5.1 归集(toList(),toSet(),toMap())
因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
[toList,toSet,toMap](src/main/java/com/ljava/somemethod/java8Stream/CollectStreamDemo.java)
#### 3.5.2 统计(count,averaging)
Collectors提供了一系列用于数据统计的静态方法：

+ 计数：count
+ 平均值：averagingInt、averagingLong、averagingDouble
+ 最值：maxBy、minBy
+ 求和：summingInt、summingLong、summingDouble
+ 统计以上所有：summarizingInt、s ummarizingLong、summarizingDouble
#### 3.5.3 分组(groupingBy/partitioningBy)
+ 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
+ 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
[partitioningBy/groupingBy](src/main/java/com/ljava/somemethod/java8Stream/GroupByStreamDemo.java)
#### 3.5.4 接合(joining)
`joining`可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
[接合](src/main/java/com/ljava/somemethod/java8Stream/JoiningStreamDemo.java)
#### 3.5.5 归约(reducing)
`Collectors`类提供的`reducing`方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
[归约](src/main/java/com/ljava/somemethod/java8Stream/ReducingStreamDemo.java)
### 3.6 排序(sort)
sorted，中间操作。有两种排序：
+ sorted()：自然排序，流中元素需实现Comparable接口
+ sorted(Comparator com)：Comparator排序器自定义排序
[排序](src/main/java/com/ljava/somemethod/java8Stream/SortedStreamDemo.java)
### 3.7 提取/组合
流也可以进行合并、去重、限制、跳过等操作。