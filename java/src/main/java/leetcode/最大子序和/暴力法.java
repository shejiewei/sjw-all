package leetcode.最大子序和;

/**
 * Created by shejiewei on 2019/12/6.
 */
public class 暴力法 {

    public static void main(String[] args) {
        int nums[] = {1, -3, 4, 5, -9, -10, 11, -12};
        int i = maxSubArray(nums);
        System.out.println("i=" + i);
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int add = 0;
            for (int j = i; j < nums.length; j++) {
                int var = nums[j];
                add = add + var;
                max = Math.max(max, add);
            }
        }
        return max;
    }
}
