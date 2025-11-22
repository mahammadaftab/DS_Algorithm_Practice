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
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        // Base case: If the root is null, return.
        if (root == null) {
            return;
        }

        // Recursively flatten the right subtree.
        flatten(root.right);

        // Recursively flatten the left subtree.
        flatten(root.left);

        // Store the left and right subtrees.
        TreeNode leftSubtree = root.left;
        TreeNode rightSubtree = root.right;

        // Set the left child of the root to null.
        root.left = null;

        // Set the right child of the root to the previous node.
        root.right = leftSubtree;

        // Find the tail of left subtree, then link the original right subtree to the tail
        TreeNode current = root;
        if(leftSubtree != null){
            current = leftSubtree;
            while(current.right != null){
                current = current.right;
            }
            current.right = rightSubtree;
        } else {
            root.right = rightSubtree;
        }

    }
}










