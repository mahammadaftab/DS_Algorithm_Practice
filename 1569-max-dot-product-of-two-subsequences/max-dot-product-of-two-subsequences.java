class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int product = nums1[i] * nums2[j];
                
                // Base choice: Start a new subsequence with just this pair
                dp[i][j] = product;
                
                // Choice 1: Extend the diagonal (if indices allow)
                if (i > 0 && j > 0) {
                    // We take max of just the product (restart) 
                    // vs product + prev (extend)
                    dp[i][j] = Math.max(dp[i][j], product + dp[i-1][j-1]);
                }
                
                // Choice 2: Inherit from skipping nums1[i]
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                }
                
                // Choice 3: Inherit from skipping nums2[j]
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[n-1][m-1];
    }
}


