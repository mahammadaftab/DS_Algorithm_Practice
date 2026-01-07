import java.util.ArrayList;
import java.util.List;

class Solution {
    // List to store the sum of every subtree encountered
    List<Long> subtreeSums = new ArrayList<>();
    
    public int maxProduct(TreeNode root) {
        // 1. Calculate sums of all subtrees and get the total tree sum
        long totalSum = getSum(root);
        
        long maxProduct = 0;
        
        // 2. Iterate through all recorded subtree sums
        for (long sum : subtreeSums) {
            // The two parts are 'sum' and 'totalSum - sum'
            long currentProduct = sum * (totalSum - sum);
            maxProduct = Math.max(maxProduct, currentProduct);
        }
        
        // 3. Return result modulo 10^9 + 7
        return (int) (maxProduct % 1_000_000_007);
    }
    
    // Helper function: Calculates sum of subtree rooted at 'node'
    private long getSum(TreeNode node) {
        if (node == null) return 0;
        
        // Recursive step: node value + sum of left subtree + sum of right subtree
        long currentSubtreeSum = node.val + getSum(node.left) + getSum(node.right);
        
        // Store this sum in our list so we can check it later
        subtreeSums.add(currentSubtreeSum);
        
        return currentSubtreeSum;
    }
}



