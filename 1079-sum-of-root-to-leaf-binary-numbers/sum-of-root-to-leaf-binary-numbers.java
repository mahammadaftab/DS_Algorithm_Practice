class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }
        
        // Shift current sum left by 1 (multiply by 2) and add the current node's bit
        currentSum = (currentSum << 1) | node.val;
        
        // If it's a leaf node, return the accumulated binary number
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Otherwise, recursively sum the left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
