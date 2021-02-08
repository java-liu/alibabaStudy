package com.sanshao.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @Description: 判断给定数组里是否有重复元素
 * @Author: Liuys
 * @CreateDate: 2021/2/8 21:15
 * @Version: 1.0
 */
public class ContainsDuplicate {
    /***
     * 判断一个整型数组中是否存在重复元素
     * @return
     */
    public static boolean containsDuplicate(int[] nums){
        if(nums.length == 0) return false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * 方法二：使用Set
     * @param nums
     * @return
     */
    public static boolean containsDuplicate1(int[] nums){
        if(nums.length == 0) return false;
        Set<Integer> setNums = new HashSet<Integer>();
        for (int num : nums) {
            setNums.add(num);
        }
        if(setNums.size() == nums.length){
            return false;
        }else{
            return true;
        }
    }
    /***
     * 方法三：使用Set，优化版本
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums){
        if(nums.length == 0) return false;
        Set<Integer> setNums = new HashSet<Integer>();
        for (int num : nums) {
            //放入set之前判断是否set已经包含，如果包含，就为false
            if(setNums.contains(num)){
                return true;
            }
            setNums.add(num);
        }
        return false;
    }

    /***
     * 方法四：排序
     * @param nums
     * @return
     */
    public static boolean containsDuplicate3(int[] nums){
        //先排序，再判断前后元素是否相等
        if(nums.length == 0) return false;
        Arrays.sort(nums);
        for(int i = 0; i< nums.length -1; ++i){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }

    /***
     * 方法五：1.8之后stream方法
     * @param nums
     * @return
     */
    public static boolean containsDuplicate4(int[] nums){
        return IntStream.of(nums).distinct().count() != nums.length;
    }


    public static void main(String[] args){
        int[] nums = {1,2,3,4,5};
        System.out.println(containsDuplicate4(nums));
    }
}
