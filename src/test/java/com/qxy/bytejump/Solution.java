package com.qxy.bytejump;




public class Solution {
    public class TreeNode {
        int val = 0;
       TreeNode left = null;
       TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @param p int整型
     * @param q int整型
     * @return int整型
     */
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        // write code here

        return lowest(root,p,q).val;
    }


    public static TreeNode lowest(TreeNode root, int p, int q){

        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowest(root.left, p, q);
        TreeNode right = lowest(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}




