package 算法.二叉树层序遍历;

import java.util.ArrayList;
import java.util.List;

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
        TreeNode node6 = new TreeNode(6);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node4.left=node6;

        //二叉树最大深度
        int depth = getDepth(node1);
        System.out.println(depth);

    }
    //二叉树反转
   public static TreeNode reverse(TreeNode root)
   {
       if(root==null)
         return null;
       TreeNode left = reverse(root.left);
       TreeNode right = reverse(root.right);

       root.left=right;
       root.right=left;
       return root;


   }


    public static int getDepth(TreeNode root){
        if(root == null)
            return 0;
        if (root.left==null&&root.right==null)
            return 1;
        int left=0;
        int right=0;
        if (root.left!=null)
        {
             left = getDepth(root.left);
        }
        if(root.right!=null)
        {
             right = getDepth(root.right);
        }


        if(left>right)
          return left+1;
        else
            return right+1;



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


    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();



        return result;
    }
}
