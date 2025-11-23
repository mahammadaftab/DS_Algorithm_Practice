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
     * Given the root node of a binary search tree (BST) and a value, 
     * find the node in the BST that the node's value equals the given value.
     * Return the subtree rooted with that node. If such a node doesn't exist, return null.
     *
     * @param root The root node of the BST.
     * @param val The value to search for.
     * @return The subtree rooted with the node whose value equals the given value, or null if not found.
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: If the tree is empty or the current node's value is equal to the target value,
        // return the current node (or null if the tree is empty).
        if (root == null || root.val == val) {
            return root;
        }

        // If the target value is less than the current node's value,
        // search in the left subtree.
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            // Otherwise, search in the right subtree.
            return searchBST(root.right, val);
        }
    }
}

