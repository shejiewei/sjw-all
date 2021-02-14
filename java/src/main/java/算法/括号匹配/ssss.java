package 算法.括号匹配;

import java.util.Stack;

/**
 * Created by shejiewei on 2021/2/13.
 */
public class ssss {

       public static void main(String[] args) {

           isValid("{[]}");


        }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        //使用foreach循环
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
