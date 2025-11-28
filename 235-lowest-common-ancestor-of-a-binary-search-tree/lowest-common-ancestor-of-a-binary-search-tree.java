/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants 
     * (where we allow a node to be a descendant of itself).”
     *
     * @param root The root of the BST.
     * @param p The first node.
     * @param q The second node.
     * @return The LCA of nodes p and q.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If the root is null, return null
        if (root == null) {
            return null;
        }

        // If both p and q are smaller than the root, the LCA is in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // If both p and q are greater than the root, the LCA is in the right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Otherwise, the root is the LCA
        return root;
    }

    // Definition for a binary tree node.
    /*public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
    }*/

    //Example usage (not part of the leetcode submission)
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        Solution solution = new Solution();
        TreeNode p = root.left; // Node 2
        TreeNode q = root.right; // Node 8

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val); // Output: 6

        p = root.left.left; // Node 0
        q = root.left.right; // Node 4

        lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val); // Output: 2
    }
}
