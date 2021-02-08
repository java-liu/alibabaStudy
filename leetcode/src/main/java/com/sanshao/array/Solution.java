package com.sanshao.array;

import java.util.Arrays;

/**
 * @Description: 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *  
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素
 *
 * @Author: Liuys
 * @CreateDate: 2021/2/1 17:11
 * @Version: 1.0
 */
public class Solution {
    public static int removeDuplicates(int[] nums){
        int newLen = 0;
        int len = nums.length;
        if(len > 1){
            Arrays.sort(nums);
            for (int j = 1; j < len; j++) {
                if(nums[newLen] == nums[j]){
                    continue;
                }else{
                    newLen++;
                    int temp = nums[newLen];
                    nums[newLen] = nums[j];
                    nums[j] = temp;
                }
            }
            newLen = newLen + 1;
        }else{
            newLen = len;
        }
        return newLen;
    }


    public static void main(String[] args){
        int[] nums = {1,1,3,4,1,5,9};
        System.out.println(removeDuplicates(nums));
        System.out.println("================================");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
