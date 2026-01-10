class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        // dp[i][j] stores the minimum ASCII delete sum for s1[0...i] and s2[0...j]
        int[][] dp = new int[n + 1][m + 1];
        
        // Base Case: Fill first row (deleting s2 to match empty s1)
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        
        // Base Case: Fill first column (deleting s1 to match empty s2)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        
        // Fill the rest of the table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                // If characters match, no deletion needed for this step
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If characters don't match, delete the one that costs less
                    // Option 1: Delete char from s1
                    int deleteS1 = dp[i - 1][j] + s1.charAt(i - 1);
                    // Option 2: Delete char from s2
                    int deleteS2 = dp[i][j - 1] + s2.charAt(j - 1);
                    
                    dp[i][j] = Math.min(deleteS1, deleteS2);
                }
            }
        }
        
        return dp[n][m];
    }
}