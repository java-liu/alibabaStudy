package com.arithmetic;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.arithmetic
 * @date 2019/8/15 11:12
 */
public class QuickSort {
    public static void main(String[] args){
        int[] a ={1,6,4,33,5,2,56,16};
        QuickSort qs = new QuickSort();
        qs.quickSort(a,0,a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a [i] + "-");
        }
    }

    public void quickSort(int [] a,int start, int end) {
        if(start < end){
            int pivot = partition(a, start, end);
            quickSort(a, start, pivot - 1);
            quickSort(a,pivot+1, end);
        }

    }
   int partition(int [] a, int start, int end){
        int i = start,j = end;
        int pivot = a [start];
        while(i < j){
            while (i < j &&a[j] >= pivot) {
                j--;
            }
            if (i < j) {
                    a[i] = a[j];
                    i++;
                }
                while (i < j && a[i] < pivot){
                    i++;
                }
            if(i < j){
                        a[j] = a[i];
                        j--;
                    }
                }
       a[i] = pivot;
       return i;
   }
}
