/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // A helper class to carry both the depth and the LCA node up the recursion stack
    class Result {
        TreeNode node;
        int dist;
        Result(TreeNode n, int d) {
            node = n;
            dist = d;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        // Base case: null node has 0 depth
        if (node == null) return new Result(null, 0);

        // Recursive step: get results from children
        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // Logic Case 1: Both sides are equally deep
        // The current node is the lowest common ancestor
        if (left.dist == right.dist) {
            return new Result(node, left.dist + 1);
        }

        // Logic Case 2: Left is deeper
        // Return the left result, but increment depth to account for current level
        if (left.dist > right.dist) {
            return new Result(left.node, left.dist + 1);
        }

        // Logic Case 3: Right is deeper
        return new Result(right.node, right.dist + 1);
    }
}




