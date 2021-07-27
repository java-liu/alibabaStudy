package com.sanshao.array;

import java.util.LinkedList;

/***
 * 写一个冒泡排序
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arrays = new int[]{3,1,4,2,9,7,8,6};
        sort(arrays);
        for (int array : arrays) {
            System.out.println(array);
        }
    }

    public static void sort(int[] arrays){
        if(arrays.length > 0){
            for (int i = 0; i < arrays.length; i++) {
                for(int j = i + 1;j < arrays.length; j++){
                    if(arrays[j] < arrays[i]){
                        int temp = arrays[i];
                        arrays[i] = arrays[j];
                        arrays[j] = temp;
                    }
                }
            }
        }
    }

    public static void linkedListTest(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        //尽量不要使用indexOf返回元素索引，并利用其进行遍历，因为当结果为空时，会遍历整个列表。效率较低
        if(linkedList.indexOf(7)==2){
            System.out.println(11);
        }
    }
}
