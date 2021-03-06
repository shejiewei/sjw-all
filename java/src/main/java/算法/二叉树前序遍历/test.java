package 算法.二叉树前序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by shejiewei on 2021/3/5.
 */
public class test {




    public static void main(String[] args) {

        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;

        List<Integer> integers=new ArrayList<>();
        List<Integer> result = preorderTraversal2(node1,integers);
        for (int i=0;i<result.size();i++)
        {
            System.out.println(result.get(i));
        }


    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> preorderTraversal2(TreeNode root,List<Integer> result ) {



        if (root.left!=null)
        {
            preorderTraversal2(root.left,result);
        }

        if(root.right!=null)
        {
            preorderTraversal2(root.right,result);
        }
        if (root == null)
            return null;
        else
            result.add(root.val);

        return result;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack();


            TreeNode p = root;

        while(p != null || !stack.isEmpty()){

                if(p!=null)
                {

                    stack.push(p);
                    p=p.left;

                }
                else {
                   p= stack.pop();
                   result.add(p.val);
                   p=p.right;
                }
            }



        return result;
    }
}
