import java.util.Arrays;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // DP state: dp[t][i][j] is min cost to reach (i, j) with 't' teleports
        // We can optimize space to use only 2 layers (current and next), 
        // but given constraints (k=10, m,n=80), full 3D array is safe and clear.
        int[][][] dp = new int[k + 1][m][n];
        
        // Initialize with infinity
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }
        
        // Base case: Start at (0, 0) with 0 cost
        dp[0][0][0] = 0;
        
        // Iterate through each number of teleportations allowed
        for (int t = 0; t <= k; t++) {
            
            // PHASE 1: Normal Moves (Right and Down) within the same layer 't'
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // Try coming from top
                    if (i > 0) {
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i - 1][j] + grid[i][j]);
                    }
                    // Try coming from left
                    if (j > 0) {
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
            
            // If we have teleports left, calculate transitions for the next layer (t + 1)
            if (t < k) {
                // minVal[v] stores the min cost to reach ANY cell with value 'v' in layer 't'
                int[] minVal = new int[10002]; // grid values up to 10^4
                Arrays.fill(minVal, Integer.MAX_VALUE / 2);
                
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int val = grid[i][j];
                        minVal[val] = Math.min(minVal[val], dp[t][i][j]);
                    }
                }
                
                // suffixMin[v] stores min cost to reach any cell with value >= v
                // Because we can teleport FROM v_src TO v_dest if v_src >= v_dest
                int[] suffixMin = new int[10002];
                int currentSuffix = Integer.MAX_VALUE / 2;
                
                // Iterate backwards to build suffix minimums
                for (int v = 10001; v >= 0; v--) {
                    currentSuffix = Math.min(currentSuffix, minVal[v]);
                    suffixMin[v] = currentSuffix;
                }
                
                // PHASE 2: Apply Teleports to layer t+1
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        // We can teleport to (i, j) from any node with value >= grid[i][j]
                        // The cost of teleport is 0.
                        int val = grid[i][j];
                        dp[t + 1][i][j] = Math.min(dp[t + 1][i][j], suffixMin[val]);
                    }
                }
            }
        }
        
        // The answer is the minimum cost to reach bottom-right with ANY number of teleports
        int result = Integer.MAX_VALUE;
        for (int t = 0; t <= k; t++) {
            result = Math.min(result, dp[t][m - 1][n - 1]);
        }
        
        return result;
    }
}