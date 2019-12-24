package leetcode.最大子序和;

/**
 * Created by shejiewei on 2019/12/6.
 */
public class 动态规划 {
    public static void main(String[] args) {
        int nums[] = {1, -3, 4, 5, -9, -10, 11, -12};
        int i = maxSubArray(nums);
        System.out.println("i=" + i);

    }

    public static int maxSubArray(int[] nums) {
        //存储以i为结尾的最大子序列和
        int[] dp = new int[nums.length];
        //第0个元素最大序列和为nums[0]
        dp[0] = nums[0];
        //最大子序列和
        int maxSum = nums[0];
        //遍历整个素组，获取以i为结尾的最大子序列和
        //当dp[i-1]>0时，dp[i]=nums[i]+dp[i-1]
        //当dp[i-1]<=0时,dp[i]=nums[i]
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            //每次比较dp[i]和maxSum取最大值
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public static int maxSubArray1(int[] nums) {

        int result;
        int max = nums[0];
        result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currsum;
            if (result > 0) {
                currsum = result + nums[i];
            } else {
                currsum = nums[i];
            }
            max = Math.max(max, currsum);

            result = currsum;


        }

        return max;
    }
}
