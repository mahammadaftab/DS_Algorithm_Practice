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
    private int maxSum = 0;

    /**
     * This class is used to return multiple values from the recursive helper function.
     * isBST: boolean indicating if the subtree is a BST.
     * minVal: Minimum value in the subtree.
     * maxVal: Maximum value in the subtree.
     * sum: Sum of all the nodes in the subtree.
     */
    private class Result {
        boolean isBST;
        int minVal;
        int maxVal;
        int sum;

        Result(boolean isBST, int minVal, int maxVal, int sum) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
        }
    }

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        traverse(root);
        return maxSum;
    }

    /**
     * Recursive helper function to traverse the tree and check if each subtree is a BST.
     * It also calculates the sum of each BST and updates the maxSum.
     */
    private Result traverse(TreeNode root) {
        if (root == null) {
            return new Result(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        Result left = traverse(root.left);
        Result right = traverse(root.right);

        // Check if the current subtree is a BST.
        boolean isBST = left.isBST && right.isBST && root.val > left.maxVal && root.val < right.minVal;

        // Calculate the sum of the current subtree.
        int sum = root.val + left.sum + right.sum;

        // Update the maxSum if the current subtree is a BST.
        if (isBST) {
            maxSum = Math.max(maxSum, sum);
        }

        // Calculate the min and max values for the current subtree.
        int minVal = Math.min(root.val, left.minVal);
        int maxVal = Math.max(root.val, right.maxVal);

        return new Result(isBST, minVal, maxVal, sum);
    }
}




