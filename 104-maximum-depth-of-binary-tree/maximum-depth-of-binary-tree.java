/**
 * Definition for a binary tree node.
 * This class represents a single node in a binary tree.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {}
    
    TreeNode(int val) { 
        this.val = val; 
    }
    
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * This class contains the solution method for finding the maximum depth.
 */
class Solution {
    /**
     * Finds the maximum depth of a binary tree.
     * The maximum depth is the number of nodes along the longest path 
     * from the root node down to the farthest leaf node.
     *
     * @param root The root of the binary tree.
     * @return The maximum depth of the binary tree.
     */
    public int maxDepth(TreeNode root) {
        // Base case: If the tree is empty (root is null), the depth is 0.
        if (root == null) {
            return 0;
        }

        // Recursively calculate the depth of the left and right subtrees.
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // The maximum depth of the tree is 1 (for the current node) 
        // plus the maximum of the left and right subtree depths.
        return 1 + Math.max(leftDepth, rightDepth);
    }
}









