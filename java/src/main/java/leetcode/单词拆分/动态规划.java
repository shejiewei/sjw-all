package leetcode.单词拆分;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/12/6.
 */
public class 动态规划 {

     public static void main(String[] args) {
        String s = "leetcode";
         List<String> wordDict =new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
         boolean b = wordBreak(s, wordDict);
         System.out.println(b);
     }

    public static boolean wordBreak(String s, List<String> wordDict) {



         return false;
    }
}
