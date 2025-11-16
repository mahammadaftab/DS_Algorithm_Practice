import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        pathSumHelper(root, targetSum, currentPath, result);
        return result;
    }

    private void pathSumHelper(TreeNode root, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        // Add the current node's value to the path
        currentPath.add(root.val);

        // Check if it's a leaf node and if the path sum matches the target
        if (root.left == null && root.right == null && root.val == targetSum) {
            // If it's a match, add a copy of the current path to the result
            result.add(new ArrayList<>(currentPath));
        }
        // Recursively explore the left and right subtrees
        else {
            pathSumHelper(root.left, targetSum - root.val, currentPath, result);
            pathSumHelper(root.right, targetSum - root.val, currentPath, result);
        }

        // Backtrack: Remove the current node's value from the path before returning
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;

        Solution solution = new Solution();
        List<List<Integer>> paths = solution.pathSum(root, targetSum);

        System.out.println("Paths with sum " + targetSum + ":");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }

}