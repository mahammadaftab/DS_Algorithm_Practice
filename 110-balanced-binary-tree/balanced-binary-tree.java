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
     * Determines if a binary tree is height-balanced.
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
     *
     * @param root The root of the binary tree.
     * @return True if the tree is height-balanced, false otherwise.
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * Recursively calculates the height of a subtree and checks for balance.
     * If the subtree is unbalanced, returns -1.
     * Otherwise, returns the height of the subtree.
     *
     * @param node The root of the subtree.
     * @return The height of the subtree, or -1 if unbalanced.
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is unbalanced
        }

        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is unbalanced
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current subtree is unbalanced
        }

        return Math.max(leftHeight, rightHeight) + 1; // Height of current subtree
    }
}


