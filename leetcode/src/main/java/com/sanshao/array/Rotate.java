package com.sanshao.array;

/**
 * @Description: 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 *  
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * @Author: Liuys
 * @CreateDate: 2021/2/7 20:34
 * @Version: 1.0
 */
public class Rotate {
    public static void rotate1(int[] arrs, int k){
        if(arrs.length == 0) return;
        int n = arrs.length;
        int[] newArray = new int[n];
        for (int i = 0; i < n; i++) {
            newArray[(i+k)%n] = arrs[i];
        }
        System.arraycopy(newArray,0 ,arrs, 0, n);
    }

    public static void rotate2(int[] arrs, int k){
        //k %= arrs.length;
        k = k%arrs.length;
        System.out.println(k);
        reverse(arrs, 0,arrs.length - 1);
        reverse(arrs, 0, k - 1);
        reverse(arrs, k ,arrs.length -1);
    }
    public static void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args){
        int[] arrs = {1,2,3,4,5,6,8};
        arrs = new int[]{1,2,3,4,5,6,7};
        //rotate1(arrs, 4);
        //print(arrs);
        //reverse(arrs,0, arrs.length-1);
        rotate2(arrs,3);
        print(arrs);
    }

    public static void print(int[] arrs){
        for (int arr : arrs) {
            System.out.print(arr + " ");
        }
    }
}
