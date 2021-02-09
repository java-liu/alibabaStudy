package com.sanshao.array;

import java.util.Arrays;

/**
 * @Description: 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @Author: Liuys
 * @CreateDate: 2021/2/9 21:25
 * @Version: 1.0
 */
public class SingleNumber {
    /***
     * 排序后，两两比较，不相等的就是要找的元素
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums){
        int target = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i = i+2) {
            if(i == nums.length - 1){
                return nums[i];
            }else{
                if(nums[i] != nums[i+1]){
                    return nums[i];
                }
            }
        }
        return target;
    }

    /***
     * 使用异或运算，
     * 异或运算，相异为真，相同为假，所以 a^a = 0 ;0^a = a
     * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
     * @param nums
     * @return
     */
    public static int singleNumber1(int[] nums){
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
           target = target^nums[i];
        }
        return target;
    }

    public static void main(String[] args){
        int[] nums = {1,1,2,2,3};
        System.out.println(singleNumber1(nums));
    }
}
