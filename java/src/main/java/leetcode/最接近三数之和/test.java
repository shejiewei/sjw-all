package leetcode.最接近三数之和;


import java.util.Arrays;

/**
 * Created by shejiewei on 2019/12/6.
 */
public class test {

     public static void main(String[] args) {
         int a=3;
         int nums[] = {-4,-1,1,2};
         int i =threeSumClosest( nums, a);
         System.out.println("i=" + i);

      }

    public static int threeSumClosest(int[] nums, int target) {

           Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];

           for (int i=0;i<nums.length;i++){
               int start=i+1;
               int end =nums.length-1;
               while (start<end){
                  int sum=nums[i]+nums[start]+nums[end];
                  if (Math.abs(target-sum)<Math.abs(target-ans)){
                          ans=sum;
                  }
                  else if (sum>target){
                      end--;
                  }
                  else if (sum<target){
                      start++;
                  }
                  else  return ans;
           }
           }
         return ans;
    }
}
