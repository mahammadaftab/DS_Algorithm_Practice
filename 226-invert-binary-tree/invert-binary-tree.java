/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
 * }
 */
class Solution {
    /**
     * Inverts a binary tree.
     * 
     * @param root The root node of the binary tree.
     * @return The root node of the inverted binary tree.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null; // Base case: empty tree
        }

        // Recursively invert the left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // Swap the left and right children
        root.left = right;
        root.right = left;

        return root; // Return the root of the inverted tree
    }
}



