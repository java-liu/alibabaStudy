package com.sanshao.array;

/****
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: Liuys
 * @CreateDate: 2021/2/20 9:33
 * @Version: 1.0
 */
public class MoveZeroes {
    /***
     * 思路及解法
     *
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     *
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     *
     * 左指针左边均为非零数；
     *
     * 右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * @param nums
     */
    public static void moveZeroes1(int[] nums){
        int n = nums.length, left = 0, right = 0;
        while(right < n){
            if(nums[right] != 0){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }
    }

    /***
     * 思路：
     * 从0开始循环，如果元素不等于0，就替换前面的元素，这样前面的元素都是不为0的，后面的全部置为0
     * @param nums
     */
    public static void moveZeroes2(int[] nums){
        int index = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(int i = index; i< n; i++){
            nums[i] = 0;
        }
    }
    public static void main(String[] args){
        int[] nums = {0,10,5,4,5,0,7};
        moveZeroes2(nums);
        Commons.print(nums);
    }
}
