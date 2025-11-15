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
    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0; // Initialize the diameter to 0
        height(root);   // Calculate the height of the tree and update the diameter
        return diameter;
    }

    // Helper function to calculate the height of a subtree rooted at 'node'
    private int height(TreeNode node) {
        if (node == null) {
            return 0; // Base case: height of an empty tree is 0
        }

        int leftHeight = height(node.left);   // Calculate height of the left subtree
        int rightHeight = height(node.right); // Calculate height of the right subtree

        // Update the diameter if the sum of left and right heights is greater than the current diameter
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the current subtree (maximum of left and right heights + 1)
        return Math.max(leftHeight, rightHeight) + 1;
    }
}








