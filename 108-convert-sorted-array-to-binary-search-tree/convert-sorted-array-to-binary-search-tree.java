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
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int left, int right) {
        // Base case: If the left pointer crosses the right pointer, it means we've reached an empty subarray.
        if (left > right) {
            return null;
        }

        // Find the middle element of the current subarray.
        int mid = left + (right - left) / 2; // Prevents integer overflow

        // Create a new node with the middle element as the root.
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively construct the left subtree using the elements to the left of the middle element.
        root.left = constructBST(nums, left, mid - 1);

        // Recursively construct the right subtree using the elements to the right of the middle element.
        root.right = constructBST(nums, mid + 1, right);

        // Return the root of the constructed subtree.
        return root;
    }

    //Definition for a binary tree node.
    /*public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }*/
}


