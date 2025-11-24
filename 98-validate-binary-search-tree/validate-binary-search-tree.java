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
    /**
     * Checks if a binary tree is a valid Binary Search Tree (BST).
     * 
     * A valid BST has the following properties for each node:
     * 1. The left subtree contains only nodes with values less than the node's value.
     * 2. The right subtree contains only nodes with values greater than the node's value.
     * 3. Both the left and right subtrees must also be BSTs.
     *
     * @param root The root node of the binary tree.
     * @return True if the tree is a valid BST, false otherwise.
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    /**
     * Recursive helper function to validate the BST.
     * 
     * @param node The current node being checked.
     * @param min The minimum allowed value for the current node (from ancestors).
     * @param max The maximum allowed value for the current node (from ancestors).
     * @return True if the subtree rooted at 'node' is a valid BST, false otherwise.
     */
    private boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true; // An empty tree is a valid BST.
        }

        // Check if the node's value is within the allowed range.
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false; // Node value violates BST property.
        }

        // Recursively check the left and right subtrees, updating the allowed ranges.
        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }
}


