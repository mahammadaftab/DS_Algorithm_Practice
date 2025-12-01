/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelper(preorder, new int[]{0}, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private TreeNode bstFromPreorderHelper(int[] preorder, int[] index, int upperBound, int lowerBound) {
        if (index[0] >= preorder.length) {
            return null;
        }

        int val = preorder[index[0]];

        if (val > upperBound || val < lowerBound) {
            return null;
        }

        TreeNode root = new TreeNode(val);
        index[0]++;

        root.left = bstFromPreorderHelper(preorder, index, val, lowerBound);
        root.right = bstFromPreorderHelper(preorder, index, upperBound, val);

        return root;
    }

    // public class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;
    //     TreeNode() {}
    //     TreeNode(int val) { this.val = val; }
    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }
}
