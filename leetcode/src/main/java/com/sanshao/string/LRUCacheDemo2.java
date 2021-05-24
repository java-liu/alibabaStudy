package com.sanshao.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 手写LRU
 * @Author: Liuys
 * @CreateDate: 2021/5/24 13:43
 * @Version: 1.0
 */
public class LRUCacheDemo2 {
    //构造一个Node节点.作为数据载体
    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;
        public Node(){
            this.prev = this.next = null;
        }
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }
    //虚拟双向链表,作为Node的载体
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;
        public DoubleLinkedList(){
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 添加到头
         * @param node 新节点
         * 先确定node前后节点,
         */
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        /***
         * 删除节点
         * @param node
         */
        public void removeNode(Node<K,V> node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;

        }
        public Node getLast(){
            return tail.prev;
        }
    }
    private int cacheSize;
    Map<Integer,Node<Integer,Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;
    public LRUCacheDemo2(int cacheSize){
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer,Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }
    public void put(int key,int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else{
            if(map.size() == cacheSize){
                Node<Integer, Integer> lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo2 lruCacheDemo2 = new LRUCacheDemo2(3);
        lruCacheDemo2.put(1,1);
        lruCacheDemo2.put(2,2);
        lruCacheDemo2.put(3,3);
        System.out.println(lruCacheDemo2.map.keySet());
        lruCacheDemo2.put(4,4);
        System.out.println(lruCacheDemo2.map.keySet());

        lruCacheDemo2.put(3,1);
        System.out.println(lruCacheDemo2.map.keySet());
        lruCacheDemo2.put(3,1);
        System.out.println(lruCacheDemo2.map.keySet());
        lruCacheDemo2.put(3,1);
        System.out.println(lruCacheDemo2.map.keySet());
        lruCacheDemo2.put(5,1);
        System.out.println(lruCacheDemo2.map.keySet());

    }
}
