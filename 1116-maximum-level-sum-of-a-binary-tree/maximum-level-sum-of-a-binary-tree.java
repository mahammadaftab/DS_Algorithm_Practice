import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE; // Initialize to smallest possible integer
        int currentLevel = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level
            int currentSum = 0;
            
            // Process all nodes at this specific level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentSum += node.val;
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            // strict inequality (>) ensures we keep the smallest level in case of a tie
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLevel = currentLevel;
            }
            
            currentLevel++;
        }
        
        return maxLevel;
    }
}


