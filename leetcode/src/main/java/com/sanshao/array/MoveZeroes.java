package com.sanshao.array;

public class MoveZeroes {
    public static void moveZeroes1(int[] nums){
        int n = nums.length, left = 0, right = 0;
            for(int l = 0; l < n;){
                for(int r = n - 1; r > l;){
                    if(nums[l] == 0){
                        int temp = nums[l];
                        nums[l] = nums[r];
                        nums[r] = temp;
                        l++;
                        r--;
                    }
                    break;
            }
        }
    }

    public static void main(String[] args){
        int[] nums = {0,10,5,4,5,0,7};
        moveZeroes1(nums);
        Commons.print(nums);
    }
}
