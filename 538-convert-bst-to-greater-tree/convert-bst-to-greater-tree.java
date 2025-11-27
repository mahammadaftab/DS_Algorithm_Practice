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
    private int sum = 0; // Maintain the sum of nodes greater than current node

    public TreeNode convertBST(TreeNode root) {
        // Perform Reverse Inorder Traversal (Right, Root, Left) to ensure we process larger values first
        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        // Traverse the right subtree
        reverseInorder(root.right);

        // Update the current node's value with the sum of greater nodes
        sum += root.val;  // Add current node's value to global sum
        root.val = sum; // Update node with sum

        // Traverse the left subtree
        reverseInorder(root.left);
    }

    // TreeNode class (for local testing - not required for LeetCode)
    /*public static class TreeNode {
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
    }*/

    // Example usage (for local testing - not required for LeetCode)
    public static void main(String[] args) {
        // Create a sample BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        // Convert the BST to a Greater Tree
        Solution solution = new Solution();
        TreeNode greaterTreeRoot = solution.convertBST(root);

        // Print the values of the converted tree (Inorder traversal)
        inorderTraversal(greaterTreeRoot);
    }

    // Helper function for inorder traversal (for local testing)
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

}

