package com.ljava.somemethod.generics;

/**
 * 一个泛型类,接口或者方法可以有多个类型参数.
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    /**
     * 多个类型参数的泛型方法示例
     * @param args
     */
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Age", 30);
        System.out.println(pair);

        Pair<Double, String> anotherPair = new Pair<>(3.14, "Pi");
        System.out.println(anotherPair);

    }
}
