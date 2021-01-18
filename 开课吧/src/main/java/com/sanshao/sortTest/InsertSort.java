package com.sanshao.sortTest;

/**
 * @Description: 插入排序
 * @Author: Liuys
 * @CreateDate: 2020/10/12 9:43
 * @Version: 1.0
 */
public class InsertSort {
    public static void sort(int[] arr){
        if(arr.length >= 2){
            int firstArr = arr[0];
            //假设第一个元素被放到了正确的位置上（只有它自己一个，当然有序）
            //这样，仅需遍历n-1次
            for(int i = 1; i < arr.length; i++){
                //从第二个元素开始，拿出来，去和它前面的元素一一对比，如果此元素小于对比的元素，就交换位置，一直到第一个元素
                int x = arr[i];
                int j = i;
                while(j > 0 && arr[j-1] > x){
                    //1、如果前一个元素大于对比的元素，就把前一个元素和对比元素交换，也就是大数会往后排
                       arr[j]  = arr[j-1];
                       j--;
                }
                //把拿出来的元素插入到最前面
                arr[j] = x;
            }
        }
    }

    public static void main(String[] args){
        //int[] arr = {2,5,11,1,3,6,18};
        int[] arr = {1,-1,0};
        //sort(arr);
        printArr(twoSum(arr, -1));


    }

    public static void printArr(int[] arr){
        if(arr != null){
            for (int i = arr.length - 1; i >= 0; i--) {
                System.out.println(arr[i]);
            }
        }
    }


    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] a= new int[2];
        boolean flag = false;
        for(int i=0; i< numbers.length; i++){
                for(int j = i+1; j < numbers.length; j++){
                    if(numbers[i] + numbers[j] == target){
                        a[1] = j;
                        a[0] = i;
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
        }
        return a;
    }

}
