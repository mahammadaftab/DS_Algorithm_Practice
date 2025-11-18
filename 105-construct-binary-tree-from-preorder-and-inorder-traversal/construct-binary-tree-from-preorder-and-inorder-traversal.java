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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        // Create a map to store the inorder indices for quick lookup
        java.util.Map<Integer, Integer> inorderIndexMap = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIndexMap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, java.util.Map<Integer, Integer> inorderIndexMap) {
        // Base case: If the subarrays are empty, return null
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // The first element in the preorder array is the root of the current subtree
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of the root in the inorder array
        int rootIndexInorder = inorderIndexMap.get(rootVal);

        // Calculate the size of the left subtree
        int leftSubtreeSize = rootIndexInorder - inStart;

        // Recursively build the left and right subtrees
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSubtreeSize, inorder, inStart, rootIndexInorder - 1, inorderIndexMap);
        root.right = buildTreeHelper(preorder, preStart + leftSubtreeSize + 1, preEnd, inorder, rootIndexInorder + 1, inEnd, inorderIndexMap);

        return root;
    }
}



