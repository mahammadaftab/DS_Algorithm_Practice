class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        long MOD = 1_000_000_007;
        
        // dp[i][j][0] -> arrays with i zeros, j ones, ending in 0
        // dp[i][j][1] -> arrays with i zeros, j ones, ending in 1
        long[][][] dp = new long[zero + 1][one + 1][2];
        
        // Base cases: arrays consisting entirely of 0s or entirely of 1s
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // Calculate valid arrays ending in 0
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    // Subtract invalid arrays (those that now have limit + 1 consecutive 0s)
                    // We add MOD before modulo to prevent negative numbers in Java
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }
                
                // Calculate valid arrays ending in 1
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    // Subtract invalid arrays (those that now have limit + 1 consecutive 1s)
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }
        
        // The answer is the sum of valid arrays ending in 0 and ending in 1
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}
