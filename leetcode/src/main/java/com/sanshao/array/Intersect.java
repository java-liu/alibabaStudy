package com.sanshao.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * @Author: Liuys
 * @CreateDate: 2021/2/10 22:54
 * @Version: 1.0
 */
public class Intersect {
    /***
     * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
     *
     * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     *
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2){

        List<Integer> targetList = new ArrayList<>();
        //Set<Integer> intSet = new HashSet<>();
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        if(nums1.length > nums2.length){
          return intersect(nums2, nums1);
        }
        Map<Integer, Integer> integerMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int count = integerMap.getOrDefault(nums1[i], 0) + 1;
            integerMap.put(nums1[i], count);
        }
        int index = 0;
        int[] intersection = new int[nums1.length];
        for (int num2 : nums2) {
            int count = integerMap.getOrDefault(num2,0);
            if(count > 0){
                intersection[index++] = num2;
                count--;
                if(count > 0){
                    integerMap.put(num2, count);
                }else{
                    integerMap.remove(num2);
                }
            }
        }
        //将一个原始数组，从下标0开始，复制到index[0,index),生成一个新的数组。
        return Arrays.copyOfRange(intersection,0 ,index);
    }

    /***
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     *
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
     *
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。
     * 当至少有一个指针超出数组范围时，遍历结束。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2){
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] intersection = new int[Math.min(length1,length2)];
        int index1 = 0, index2 = 0, index = 0;
        while(index1 < length1 && index2 < length2){
            if(nums1[index1] < nums2[index2]){
                index1++;
            }else if(nums1[index1] > nums2[index2]){
                index2++;
            }else{
                intersection[index] = nums1[index1];
                index++;
                index1++;
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection,0 ,index);
    }

    private static int[] SetToInt(Set<Integer> allSet){
        Integer[] temp = allSet.toArray(new Integer[] {});

        int[] intArray = new int[temp.length];
        for(int i = 0; i< temp.length; i++){
            intArray[i] = temp[i].intValue();
        }
        return intArray;
    }

    private static void print(int[] nums){
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        int[] nums1 = {1,2,1};
        int[] nums2 = {1,1};
        print(intersect2(nums1, nums2));
    }
}
