import java.util.Arrays;

class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m][n][3];
        
        // Use a safe minimum to prevent integer underflow when adding negative numbers
        int MIN = -1_000_000_000; 
        
        // Initialize all states to invalid/unreachable
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], MIN);
            }
        }
        
        // Base cases for the starting cell
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0; // Shield used on first cell
            dp[0][0][2] = 0; // Redundant, but valid state
        } else {
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        }
        
        // Fill the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int val = coins[i][j];
                
                for (int k = 0; k < 3; k++) {
                    // Option 1: Do not use a shield on this cell
                    int prevMax = MIN;
                    if (i > 0) prevMax = Math.max(prevMax, dp[i - 1][j][k]);
                    if (j > 0) prevMax = Math.max(prevMax, dp[i][j - 1][k]);
                    
                    if (prevMax != MIN) {
                        dp[i][j][k] = prevMax + val;
                    }
                    
                    // Option 2: Use a shield on this cell (if negative and k > 0)
                    if (val < 0 && k > 0) {
                        int prevMaxShield = MIN;
                        if (i > 0) prevMaxShield = Math.max(prevMaxShield, dp[i - 1][j][k - 1]);
                        if (j > 0) prevMaxShield = Math.max(prevMaxShield, dp[i][j - 1][k - 1]);
                        
                        if (prevMaxShield != MIN) {
                            // +0 because the negative value is neutralized
                            dp[i][j][k] = Math.max(dp[i][j][k], prevMaxShield); 
                        }
                    }
                }
            }
        }
        
        // The answer is the maximum money achieved at the destination across any number of shields used
        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}
